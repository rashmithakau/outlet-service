package com.lloms.outlet_service.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lloms.outlet_service.dto.CustomerOrderItemDTO;
import com.lloms.outlet_service.dto.request.CustomerOrderRequestDTO;
import com.lloms.outlet_service.dto.response.CusOrderResponseDTO;
import com.lloms.outlet_service.service.CustomerOrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerOrderController.class)
class CustomerOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerOrderService customerOrderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindAllByOutletId() throws Exception {
        List<CusOrderResponseDTO> orders = List.of(new CusOrderResponseDTO());
        when(customerOrderService.findAllByOutletId(1)).thenReturn(orders);

        mockMvc.perform(get("/api/v1/cus-order/all-by-outlet/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully fetch all cusOrders"));
    }

    @Test
    void testGetCusOrderItems() throws Exception {
        List<CustomerOrderItemDTO> items = List.of(new CustomerOrderItemDTO());
        when(customerOrderService.getCusOrderItems(1)).thenReturn(items);

        mockMvc.perform(get("/api/v1/cus-order/items?cusOrId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Successfully fetch all cusOrders"));
    }

    @Test
    void testFindAllByOutletIdAndDate() throws Exception {
        when(customerOrderService.findAllByOutletIdAndDate(1, "2025-04-01"))
                .thenReturn(Collections.singletonList(new CusOrderResponseDTO()));

        mockMvc.perform(get("/api/v1/cus-order/all-by-outlet-date/1/2025-04-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testFindAllByOutletIdAndMonthAndDate() throws Exception {
        when(customerOrderService.findAllByOutletIdAndMonthAndDate(1, 2025, 4))
                .thenReturn(List.of(new CusOrderResponseDTO()));

        mockMvc.perform(get("/api/v1/cus-order/all-by-outlet-year-month/1/2025/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }
}
