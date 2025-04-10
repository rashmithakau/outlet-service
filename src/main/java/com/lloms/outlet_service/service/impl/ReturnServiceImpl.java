package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.OutletReturnDTO;
import com.lloms.outlet_service.dto.ReturnItemDto;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.entity.OutletReturn;
import com.lloms.outlet_service.entity.ReturnItem;
import com.lloms.outlet_service.enums.OutletReturnStatus;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.repository.OutletReturnRepository;
import com.lloms.outlet_service.service.ReturnService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReturnServiceImpl implements ReturnService {

    private final OutletReturnRepository outletReturnRepository;
    private final ModelMapper modelMapper;
    private final OutletRepository outletRepository;

    @Override
    @Transactional
    public void saveReturn(OutletReturnDTO returnDTO) {
        // Fetch the outlet safely
        Outlet outlet = outletRepository.findById(returnDTO.getOutletId())
                .orElseThrow(() -> new RuntimeException("Outlet not found"));

        // Map OutletReturnDTO to OutletReturn
        OutletReturn outletReturn = modelMapper.map(returnDTO, OutletReturn.class);
        outletReturn.setOutlet(outlet);

        // Map and associate ReturnItems using stream for cleaner code
        List<ReturnItem> returnItems = returnDTO.getReturnItems().stream()
                .map(dto -> {
                    ReturnItem item = modelMapper.map(dto, ReturnItem.class);
                    item.setOutletReturn(outletReturn);
                    return item;
                }).collect(Collectors.toList());

        outletReturn.setReturnItems(returnItems); // Set the list of return items

        // Save OutletReturn with return items (CascadeType.ALL handles saving items)
       outletReturnRepository.save(outletReturn);
    }

    @Override
    public void updateReturnStatus(Integer returnId, OutletReturnStatus status) {
        OutletReturn outletReturn =outletReturnRepository.findByreturnId(returnId);
        outletReturn.setOutletReturnStatus(status);
        outletReturnRepository.save(outletReturn);
    }

    @Override
    public List<OutletReturnDTO> getAllReturns() {
        List<OutletReturn> listOutletsReturns = outletReturnRepository.findAllWithItems(); // use new method
        List<OutletReturnDTO> outletReturnDTOs = new ArrayList<>();

        listOutletsReturns.forEach(outletReturn -> {
            OutletReturnDTO outletReturnDTO = new OutletReturnDTO();
            outletReturnDTO.setReturnId(outletReturn.getReturnId());
            outletReturnDTO.setReturnDate(outletReturn.getReturnDate());
            outletReturnDTO.setOutletId(outletReturn.getOutlet().getOutletId());
            outletReturnDTO.setOutletReturnStatus(outletReturn.getOutletReturnStatus());
            outletReturnDTO.setOutletName(outletReturn.getOutlet().getOutletName());

            List<ReturnItemDto> returnItems = outletReturn.getReturnItems().stream()
                    .map(ety -> {
                        ReturnItemDto item = new ReturnItemDto();
                        item.setProductId(ety.getProductId());
                        item.setQuantity(ety.getQuantity());
                        item.setReason(ety.getReason());
                        item.setProductName(ety.getProductName());
                        item.setUnitPrice(ety.getUnitPrice());
                        return item;
                    }).collect(Collectors.toList());

            outletReturnDTO.setReturnItems(returnItems);
            outletReturnDTOs.add(outletReturnDTO);
        });

        return outletReturnDTOs;
    }

    @Override
    public List<OutletReturnDTO> getAllReturnsByStatus(OutletReturnStatus status) {
        List<OutletReturn> listOutletsReturns = outletReturnRepository.findAllByOutletReturnStatus(status); // use new method
        List<OutletReturnDTO> outletReturnDTOs = new ArrayList<>();

        listOutletsReturns.forEach(outletReturn -> {
            OutletReturnDTO outletReturnDTO = new OutletReturnDTO();
            outletReturnDTO.setReturnId(outletReturn.getReturnId());
            outletReturnDTO.setReturnDate(outletReturn.getReturnDate());
            outletReturnDTO.setOutletId(outletReturn.getOutlet().getOutletId());
            outletReturnDTO.setOutletReturnStatus(outletReturn.getOutletReturnStatus());
            outletReturnDTO.setOutletName(outletReturn.getOutlet().getOutletName());

            List<ReturnItemDto> returnItems = outletReturn.getReturnItems().stream()
                    .map(ety -> {
                        ReturnItemDto item = new ReturnItemDto();
                        item.setProductId(ety.getProductId());
                        item.setQuantity(ety.getQuantity());
                        item.setReason(ety.getReason());
                        item.setProductName(ety.getProductName());
                        item.setUnitPrice(ety.getUnitPrice());
                        return item;
                    }).collect(Collectors.toList());

            outletReturnDTO.setReturnItems(returnItems);
            outletReturnDTOs.add(outletReturnDTO);
        });

        return outletReturnDTOs;
    }

    @Override
    public List<OutletReturnDTO> getAllReturnsByOutletId(Integer outletId) {
        List<OutletReturn> listOutletsReturns = outletReturnRepository.getAllByOutletWithItems(outletRepository.getReferenceById(outletId));
// use new method
        List<OutletReturnDTO> outletReturnDTOs = new ArrayList<>();

        listOutletsReturns.forEach(outletReturn -> {
            OutletReturnDTO outletReturnDTO = new OutletReturnDTO();
            outletReturnDTO.setReturnId(outletReturn.getReturnId());
            outletReturnDTO.setReturnDate(outletReturn.getReturnDate());
            outletReturnDTO.setOutletId(outletReturn.getOutlet().getOutletId());
            outletReturnDTO.setOutletReturnStatus(outletReturn.getOutletReturnStatus());
            outletReturnDTO.setOutletName(outletReturn.getOutlet().getOutletName());

            List<ReturnItemDto> returnItems = outletReturn.getReturnItems().stream()
                    .map(ety -> {
                        ReturnItemDto item = new ReturnItemDto();
                        item.setProductId(ety.getProductId());
                        item.setQuantity(ety.getQuantity());
                        item.setReason(ety.getReason());
                        item.setProductName(ety.getProductName());
                        item.setUnitPrice(ety.getUnitPrice());
                        return item;
                    }).collect(Collectors.toList());

            outletReturnDTO.setReturnItems(returnItems);
            outletReturnDTOs.add(outletReturnDTO);
        });

        return outletReturnDTOs;
    }

    @Override
    public List<OutletReturnDTO> getAllReturnsNotPending() {
        List<OutletReturnStatus> statuses = Arrays.asList(
                OutletReturnStatus.Approved,
                OutletReturnStatus.Rejected
        );

        List<OutletReturn> listOutletsReturns = outletReturnRepository.findReturnsWithItemsNotPending(); // use the correct JPA query method
        List<OutletReturnDTO> outletReturnDTOs = new ArrayList<>();

        for (OutletReturn outletReturn : listOutletsReturns) {
            OutletReturnDTO outletReturnDTO = new OutletReturnDTO();
            outletReturnDTO.setReturnId(outletReturn.getReturnId());
            outletReturnDTO.setReturnDate(outletReturn.getReturnDate());
            outletReturnDTO.setOutletId(outletReturn.getOutlet().getOutletId());
            outletReturnDTO.setOutletReturnStatus(outletReturn.getOutletReturnStatus());
            outletReturnDTO.setOutletName(outletReturn.getOutlet().getOutletName());

            List<ReturnItemDto> returnItems = outletReturn.getReturnItems().stream()
                    .map(ety -> {
                        ReturnItemDto item = new ReturnItemDto();
                        item.setProductId(ety.getProductId());
                        item.setQuantity(ety.getQuantity());
                        item.setReason(ety.getReason());
                        item.setProductName(ety.getProductName());
                        item.setUnitPrice(ety.getUnitPrice());
                        return item;
                    }).collect(Collectors.toList());

            outletReturnDTO.setReturnItems(returnItems);
            outletReturnDTOs.add(outletReturnDTO);
        }

        return outletReturnDTOs;
    }



}
