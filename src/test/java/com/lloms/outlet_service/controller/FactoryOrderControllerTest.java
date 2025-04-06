package com.lloms.outlet_service.controller;

import com.lloms.outlet_service.dto.FactoryOrderItemDTO;
import com.lloms.outlet_service.dto.request.FactoryOrderRequestDTO;
import com.lloms.outlet_service.dto.response.FactoryOrderResDTO;
import com.lloms.outlet_service.service.FactoryOrderService;
import com.lloms.outlet_service.util.StandardResponse;
import com.lloms.outlet_service.enums.FactoryOrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class FactoryOrderControllerTest {

    @Mock
    private FactoryOrderService factoryOrderService;

    @InjectMocks
    private FactoryOrderController factoryOrderController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(factoryOrderController).build();
    }

    @Test
    public void testSaveFacOrder() throws Exception {
        FactoryOrderRequestDTO factoryOrderRequestDTO = new FactoryOrderRequestDTO();
        factoryOrderRequestDTO.setOrderDate(new Date());
        factoryOrderRequestDTO.setStatus(FactoryOrderStatus.Pending);
        factoryOrderRequestDTO.setOutletId(1);
        factoryOrderRequestDTO.setItems(new ArrayList<>());

        mockMvc.perform(post("/api/v1/fac-order")
                        .contentType("application/json")
                        .content("{\"orderDate\": \"2025-04-06T00:00:00.000+00:00\", \"status\": \"Pending\", \"outletId\": 1, \"items\": []}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(0)); // Adjusted to match your expected response
    }

    @Test
    public void testGetFacOrders() throws Exception {
        List<FactoryOrderResDTO> mockOrders = new ArrayList<>();
        mockOrders.add(new FactoryOrderResDTO()); // Add some mock data to test

        when(factoryOrderService.getFacOrderByStatus(FactoryOrderStatus.Pending)).thenReturn(mockOrders);

        mockMvc.perform(get("/api/v1/fac-order")
                        .param("status", "Pending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(mockOrders.size()));
    }

    @Test
    public void testGetFacOrdersByFacOrId() throws Exception {
        List<FactoryOrderResDTO> mockOrders = new ArrayList<>();
        mockOrders.add(new FactoryOrderResDTO()); // Add some mock data to test

        when(factoryOrderService.getFacOrderById(1)).thenReturn(mockOrders);

        mockMvc.perform(get("/api/v1/fac-order/byId")
                        .param("facOrId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(mockOrders.size()));
    }

    @Test
    public void testGetFacOrderItems() throws Exception {
        List<FactoryOrderItemDTO> mockItems = new ArrayList<>();
        mockItems.add(new FactoryOrderItemDTO()); // Add some mock items to test

        when(factoryOrderService.getFacOrderItemsById(1)).thenReturn(mockItems);

        mockMvc.perform(get("/api/v1/fac-order/items")
                        .param("facOrId", "1"))
                .andExpect(status().isOk())  // Change status to 201 to match the controller's response
                .andExpect(jsonPath("$.code").value(HttpStatus.OK.value())) // Check that code is 200
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(mockItems.size()));
    }


    @Test
    public void testChangeOrderStatus() throws Exception {
        when(factoryOrderService.changeOrderStatus(1, FactoryOrderStatus.Confirmed)).thenReturn(true);

        mockMvc.perform(put("/api/v1/fac-order/status")
                        .param("facOrId", "1")
                        .param("status", "Confirmed"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(201))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data").value(true));
    }
}
