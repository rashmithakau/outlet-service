package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.OutletDTO;
import com.lloms.outlet_service.dto.request.OutletSaveRequestDTO;
import com.lloms.outlet_service.dto.request.RequestUpdateOutletDTO;
import com.lloms.outlet_service.dto.response.ResponseGetOutletDTO;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.enums.OutletStatus;
import com.lloms.outlet_service.exception.NotFoundException;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.service.OutletService;
import com.lloms.outlet_service.util.StandardResponse;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OutletServiceIMPL implements OutletService {
    private final OutletRepository outletRepository;
    private final ModelMapper modelMapper;
    private final String IMAGE_UPLOAD_DIR = "src/main/java/com/lloms/outlet_service/assets/";

    @Override
    public OutletDTO saveOutlet(OutletSaveRequestDTO outletSaveRequestDTO) {
        MultipartFile image = outletSaveRequestDTO.getImageFile();
        String imageUrl = null;

        if (image != null && !image.isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                Path imagePath = Paths.get(IMAGE_UPLOAD_DIR + fileName);
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, image.getBytes());
                imageUrl = imagePath.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Outlet outlet = modelMapper.map(outletSaveRequestDTO, Outlet.class);
        outlet.setImageUrl(imageUrl);

        // ✅ Just save directly
        Outlet saved = outletRepository.save(outlet);
        return modelMapper.map(saved, OutletDTO.class);
    }


    @Override
    public ResponseGetOutletDTO getOutletById(int outletId) {
        if(outletRepository.existsById(outletId)){
            Outlet outlet = outletRepository.findById(outletId).get();
            return modelMapper.map(outlet,ResponseGetOutletDTO.class);
        }else {
            throw new NotFoundException("No outlet found!");
        }
    }

    @Override
    public List<ResponseGetOutletDTO> getAllOutlets() {
        List<Outlet> outlets = outletRepository.findAllByStatusEquals(OutletStatus.Active);
        return outlets.stream()
                .map(outlet -> modelMapper.map(outlet,ResponseGetOutletDTO.class))
                .toList();

    }

    @Override
    public ResponseGetOutletDTO updateOutlet(RequestUpdateOutletDTO requestUpdateOutletDTO, int outletID) {
        Optional<Outlet> outletToUpdate = outletRepository.findById(outletID);
        if(outletToUpdate.isPresent()){
            Outlet outlet = outletToUpdate.get();

            outlet.setOutletName(requestUpdateOutletDTO.getOutletName());
            outlet.setLocation(requestUpdateOutletDTO.getLocation());
            outlet.setStatus(requestUpdateOutletDTO.getStatus());

            Outlet updatedOutlet = outletRepository.save(outlet);
            return modelMapper.map(updatedOutlet, ResponseGetOutletDTO.class);
        } else {
            throw new NotFoundException("Outlet not found with Id "+ outletID);
        }
    }

    @Override
    public Resource getImageByUrl(String url) {
        String imagePath = IMAGE_UPLOAD_DIR + url;
        File file = new File(imagePath);

        // Ensure the file exists
        if (!file.exists()) {
            throw new RuntimeException("Image not found: " + url);
        }

        // Create a resource from the image file
        return new FileSystemResource(file);
    }

    @Override
    public OutletDTO updateOutletStatus(int outletID) {
        // Find outlet by ID
        Outlet outlet = outletRepository.findById(outletID)
                .orElseThrow(() -> new RuntimeException("Outlet not found with ID: " + outletID));

        // Set outlet status to Active
        outlet.setStatus(OutletStatus.Active);

        // Save the updated outlet
        Outlet updatedOutlet = outletRepository.save(outlet);

        // Convert to DTO
        return modelMapper.map(updatedOutlet, OutletDTO.class);
    }

    @Override
    public List<ResponseGetOutletDTO> getIncativeOutlets() {
        List<Outlet> outlets = outletRepository.findAllByStatusEquals(OutletStatus.Inactive);
        return outlets.stream()
                .map(outlet -> modelMapper.map(outlet,ResponseGetOutletDTO.class))
                .toList();
    }

    @Override
    public List<ResponseGetOutletDTO> getAllActiveAndInactiveOutlets() {
        List<Outlet> outlets = outletRepository.findAll();
        return outlets.stream()
                .map(outlet -> modelMapper.map(outlet,ResponseGetOutletDTO.class))
                .toList();
    }
}
