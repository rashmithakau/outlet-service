package com.lloms.outlet_service.service.intercommunication;

import com.lloms.outlet_service.dto.intercommunication.RequestDateAndPriceListDTO;
import com.lloms.outlet_service.dto.intercommunication.ResponseGetAllProductsDTO;
import com.lloms.outlet_service.util.StandardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8080/api/v1/price",value = "product-service")
public interface ProductApiClient {
    @PostMapping("/list-by-product-ids")
    public ResponseEntity<StandardResponse<List<ResponseGetAllProductsDTO>>> getPriceListByDateAndProductIdList(
            @RequestBody RequestDateAndPriceListDTO requestDateAndPriceListDTO);
}
