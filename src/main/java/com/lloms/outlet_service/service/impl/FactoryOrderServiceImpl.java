package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.FactoryOrderItemDTO;
import com.lloms.outlet_service.dto.intercommunication.RequestDateAndPriceListDTO;
import com.lloms.outlet_service.dto.intercommunication.ResponseGetAllProductsDTO;
import com.lloms.outlet_service.dto.request.FactoryOrderRequestDTO;
import com.lloms.outlet_service.dto.response.FactoryOrderResDTO;
import com.lloms.outlet_service.entity.FactoryOrder;
import com.lloms.outlet_service.entity.FactoryOrderItem;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.enums.FactoryOrderStatus;
import com.lloms.outlet_service.repository.FacOrderItemRepository;
import com.lloms.outlet_service.repository.FactoryOrderRepository;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.service.FactoryOrderService;
import com.lloms.outlet_service.service.intercommunication.ProductApiClient;
import com.lloms.outlet_service.util.StandardResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class FactoryOrderServiceImpl implements FactoryOrderService {
    private final FactoryOrderRepository factoryOrderRepository;
    private final OutletRepository outletRepository;
    private final FacOrderItemRepository facOrderItemRepository;
    private final ModelMapper modelMapper;
    private final ProductApiClient productApiClient;


    @Override
    public void saveFacOrder(FactoryOrderRequestDTO facOrdReq) {
        // Fetch the outlet safely
        Outlet outlet = outletRepository.findById(facOrdReq.getOutletId())
                .orElseThrow(() -> new RuntimeException("Outlet not found"));

        // Map FactoryOrderRequestDTO to FactoryOrder
        FactoryOrder facOrder = modelMapper.map(facOrdReq, FactoryOrder.class);
        facOrder.setOutlet(outlet);

        // Map and associate FactoryOrderItems
        List<FactoryOrderItem> orderItems = new ArrayList<>();
        for (FactoryOrderItemDTO factoryOrderItemDTO : facOrdReq.getItems()) {
            FactoryOrderItem orderItem = modelMapper.map(factoryOrderItemDTO, FactoryOrderItem.class);
            orderItem.setFactoryOrder(facOrder);  // Associate with FactoryOrder
            orderItems.add(orderItem);
        }

        facOrder.setFactoryOrderItems(orderItems);

        // Save FactoryOrder with items (CascadeType.ALL handles saving items)
        factoryOrderRepository.save(facOrder);
    }

    @Override
    public List<FactoryOrderResDTO> getFacOrderByStatus(FactoryOrderStatus status) {
        List<FactoryOrder> factoryOrderList = factoryOrderRepository.getFactoryOrderByStatus(status);

        List<FactoryOrderResDTO> factoryOrderResDTOList = new ArrayList<>();
        factoryOrderList.forEach(factoryOrder -> {
            FactoryOrderResDTO factoryOrderResDTO = modelMapper.map(factoryOrder, FactoryOrderResDTO.class);
            factoryOrderResDTO.setOutletName(factoryOrder.getOutlet().getOutletName());
            factoryOrderResDTOList.add(factoryOrderResDTO);
        });

        StandardResponse<List<ResponseGetAllProductsDTO>> res = productApiClient.getPriceListByDateAndProductIdList(
                new RequestDateAndPriceListDTO("2025-12-12", new ArrayList<Long>(Arrays.asList(1L)))).getBody();

        if (res != null && res.getData() != null) {
            // Assuming the data is a list of ResponseGetAllProductsDTO
            List<ResponseGetAllProductsDTO> productsList = res.getData();
            productsList.forEach(product -> {
                System.out.println(product.getProductId());  // Adjust based on the fields in ResponseGetAllProductsDTO
            });
        } else {
            System.out.println("No data received or response is null.");
        }


        return factoryOrderResDTOList;
    }

    @Override
    public List<FactoryOrderItemDTO> getFacOrderItemsById(Integer facOrId) {
        List<Long> productIds = new ArrayList<>();
        List<FactoryOrderItem> factoryOrderItemList = facOrderItemRepository.getFactoryOrderItemsByFactoryOrder_FacOrderId(facOrId);
        FactoryOrder facOrder = factoryOrderRepository.findById(facOrId).orElseThrow(RuntimeException::new);


        factoryOrderItemList.forEach(item -> {
            productIds.add(Long.valueOf(item.getProductId()));  // More explicit cast from int to long
        });

        // Call to external API, ensure you handle potential null responses
        StandardResponse<List<ResponseGetAllProductsDTO>> res = productApiClient.getPriceListByDateAndProductIdList(
                new RequestDateAndPriceListDTO(facOrder.getOrderDate().toString(), productIds)).getBody();

        // Null check before proceeding
        if (res == null || res.getData() == null) {
            // Handle the case where no data was returned
            return new ArrayList<>();  // Return empty list if no data found
        }

        List<ResponseGetAllProductsDTO> productsList = res.getData();
        System.out.println(productsList.get(0).getProductName()+" "+productsList.get(0).getProductId());
        HashMap<Long, ResponseGetAllProductsDTO> productsMap = new HashMap<>();

        productsList.forEach(product -> {
            productsMap.put(product.getProductId(), product);
        });

        List<FactoryOrderItemDTO> factoryOrderItemDTOList = new ArrayList<>();

        factoryOrderItemList.forEach(factoryOrderItem -> {
            FactoryOrderItemDTO factoryOrderItemDTO = modelMapper.map(factoryOrderItem, FactoryOrderItemDTO.class);
            // Avoid null if no product found in map
            ResponseGetAllProductsDTO product = productsMap.get(Long.valueOf(factoryOrderItem.getProductId()));
            if (product != null) {
                factoryOrderItemDTO.setProductName(product.getProductName());
                factoryOrderItemDTO.setUnitPrice(product.getPrice());
            }
            factoryOrderItemDTOList.add(factoryOrderItemDTO);
        });

        return factoryOrderItemDTOList;
    }

    @Override
    public boolean changeOrderStatus(Integer facOrId, FactoryOrderStatus status) {
        try {
            FactoryOrder facOrder = factoryOrderRepository.findById(facOrId).orElseThrow(RuntimeException::new);
            facOrder.setStatus(status);
             factoryOrderRepository.save(facOrder);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<FactoryOrderResDTO> getFacOrderById(int facOrId) {
        List<FactoryOrder> factoryOrderList = factoryOrderRepository.getFactoryOrderByFacOrderId(facOrId);
        List<FactoryOrderResDTO> factoryOrderResDTOList = new ArrayList<>();
        factoryOrderList.forEach(factoryOrder -> {
            FactoryOrderResDTO factoryOrderResDTO = modelMapper.map(factoryOrder, FactoryOrderResDTO.class);
            factoryOrderResDTO.setOutletName(factoryOrder.getOutlet().getOutletName());
            factoryOrderResDTOList.add(factoryOrderResDTO);
        });

        StandardResponse<List<ResponseGetAllProductsDTO>> res = productApiClient.getPriceListByDateAndProductIdList(
                new RequestDateAndPriceListDTO("2025-12-12", new ArrayList<Long>(Arrays.asList(1L)))).getBody();

        if (res != null && res.getData() != null) {
            // Assuming the data is a list of ResponseGetAllProductsDTO
            List<ResponseGetAllProductsDTO> productsList = res.getData();
            productsList.forEach(product -> {
                System.out.println(product.getProductId());  // Adjust based on the fields in ResponseGetAllProductsDTO
            });
        } else {
            System.out.println("No data received or response is null.");
        }


        return factoryOrderResDTOList;
    }

    @Override
    public List<FactoryOrderResDTO> getFacOrdersByOutletId(Integer outId) {
        Outlet outlet = outletRepository.findById(outId).orElseThrow(RuntimeException::new);
        List<FactoryOrder> factoryOrderList = factoryOrderRepository.getFactoryOrderByOutlet(outlet);
        List<FactoryOrderResDTO> factoryOrderResDTOList = new ArrayList<>();
        factoryOrderList.forEach(factoryOrder -> {
            FactoryOrderResDTO factoryOrderResDTO = modelMapper.map(factoryOrder, FactoryOrderResDTO.class);
            factoryOrderResDTO.setOutletName(factoryOrder.getOutlet().getOutletName());
            factoryOrderResDTOList.add(factoryOrderResDTO);
        });

        StandardResponse<List<ResponseGetAllProductsDTO>> res = productApiClient.getPriceListByDateAndProductIdList(
                new RequestDateAndPriceListDTO("2025-12-12", new ArrayList<Long>(Arrays.asList(1L)))).getBody();

        if (res != null && res.getData() != null) {
            // Assuming the data is a list of ResponseGetAllProductsDTO
            List<ResponseGetAllProductsDTO> productsList = res.getData();
            productsList.forEach(product -> {
                System.out.println(product.getProductId());  // Adjust based on the fields in ResponseGetAllProductsDTO
            });
        } else {
            System.out.println("No data received or response is null.");
        }


        return factoryOrderResDTOList;
    }
}
