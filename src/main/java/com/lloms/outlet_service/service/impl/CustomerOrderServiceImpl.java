package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.CustomerOrderItemDTO;
import com.lloms.outlet_service.dto.FactoryOrderItemDTO;
import com.lloms.outlet_service.dto.intercommunication.RequestDateAndPriceListDTO;
import com.lloms.outlet_service.dto.intercommunication.ResponseGetAllProductsDTO;
import com.lloms.outlet_service.dto.request.CustomerOrderRequestDTO;
import com.lloms.outlet_service.dto.response.CusOrderResponseDTO;
import com.lloms.outlet_service.entity.*;
import com.lloms.outlet_service.repository.CusOrderItemRepository;
import com.lloms.outlet_service.repository.CustomerOrderRepository;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.service.CustomerOrderService;
import com.lloms.outlet_service.service.intercommunication.ProductApiClient;
import com.lloms.outlet_service.util.StandardResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepository customerOrderRepository;
    private final CusOrderItemRepository cusOrderItemRepository;
    private final OutletRepository outletRepository;
    private final ProductApiClient productApiClient;
    private ModelMapper modelMapper;

    @Transactional
    public void saveFacOrder(CustomerOrderRequestDTO cusOrdReq) {
        // Fetch the outlet safely
        Outlet outlet = outletRepository.findById(cusOrdReq.getOutletId())
                .orElseThrow(() -> new RuntimeException("Outlet not found"));

        // Map CustomerOrderRequestDTO to CustomerOrder
        CustomerOrder customerOrder = modelMapper.map(cusOrdReq, CustomerOrder.class);
        customerOrder.setOutlet(outlet);

        // Map and associate CustomerOrderItems using stream for cleaner code
        List<CustomerOrderItem> orderItems = cusOrdReq.getItems().stream()
                .map(dto -> {
                    CustomerOrderItem item = modelMapper.map(dto, CustomerOrderItem.class);
                    item.setCustomerOrder(customerOrder);
                    return item;
                }).collect(Collectors.toList());

        customerOrder.setStatus(cusOrdReq.getStatus());
        customerOrder.setCustomerOrderItem(orderItems);

        // Save CustomerOrder with items (CascadeType.ALL handles saving items)
        customerOrderRepository.save(customerOrder);
    }

    @Override
    public List<CusOrderResponseDTO> findAllByOutletId(int outletId) {
        List<CustomerOrder> orders = customerOrderRepository.findAll()
                .stream()
                .filter(order -> order.getOutlet().getOutletId().equals(outletId))
                .toList();

        return orders.stream()
                .map(order -> new CusOrderResponseDTO(
                        order.getCusOrderID(),
                        order.getOutlet().getOutletId().toString(),
                        order.getOrderDate(),
                        order.getStatus(),
                        order.getCustomerName(),
                        order.getCustomerPhone()
                ))
                .toList();
    }

    @Override
    public List<CustomerOrderItemDTO> getCusOrderItems(Integer cusOrId) {
        List<Long> productIds = new ArrayList<>();
        List<CustomerOrderItem> customerOrderItemList = cusOrderItemRepository.getCustomerOrderItemsByCustomerOrder_CusOrderID(cusOrId);
        CustomerOrder cusOrder = customerOrderRepository.findById(cusOrId).orElseThrow( RuntimeException::new);

        customerOrderItemList.forEach(customerOrderItem -> {
            productIds.add(Long.valueOf(customerOrderItem.getProductId()));
        });

        // Call to external API, ensure you handle potential null responses
        StandardResponse<List<ResponseGetAllProductsDTO>> res = productApiClient.getPriceListByDateAndProductIdList(
                new RequestDateAndPriceListDTO(cusOrder.getOrderDate().toString(), productIds)).getBody();

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

        List<CustomerOrderItemDTO> customerOrderItemDTOList = new ArrayList<>();

        customerOrderItemList.forEach(customerOrderItem -> {
            CustomerOrderItemDTO customerOrderItemDTO = modelMapper.map(customerOrderItem, CustomerOrderItemDTO.class);
            // Avoid null if no product found in map
            ResponseGetAllProductsDTO product = productsMap.get(Long.valueOf(customerOrderItem.getProductId()));
            if (product != null) {
                customerOrderItemDTO.setProductName(product.getProductName());
                customerOrderItemDTO.setUnitPrice(product.getPrice());
            }
            customerOrderItemDTOList.add(customerOrderItemDTO);
        });

        return customerOrderItemDTOList;
    }

}
