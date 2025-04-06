package com.lloms.outlet_service.service.impl;

import com.lloms.outlet_service.dto.CustomerOrderItemDTO;
import com.lloms.outlet_service.dto.intercommunication.RequestDateAndPriceListDTO;
import com.lloms.outlet_service.dto.intercommunication.ResponseGetAllProductsDTO;
import com.lloms.outlet_service.dto.request.CustomerOrderRequestDTO;
import com.lloms.outlet_service.dto.response.CusOrderResponseDTO;
import com.lloms.outlet_service.entity.CustomerOrder;
import com.lloms.outlet_service.entity.CustomerOrderItem;
import com.lloms.outlet_service.entity.Outlet;
import com.lloms.outlet_service.enums.CustomerOrderStatus;
import com.lloms.outlet_service.repository.CusOrderItemRepository;
import com.lloms.outlet_service.repository.CustomerOrderRepository;
import com.lloms.outlet_service.repository.OutletRepository;
import com.lloms.outlet_service.service.intercommunication.ProductApiClient;
import com.lloms.outlet_service.util.StandardResponse;
import com.lloms.outlet_service.util.functions.ServiceFunctions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerOrderServiceImplTest {

    @Mock
    private CustomerOrderRepository customerOrderRepository;

    @Mock
    private CusOrderItemRepository cusOrderItemRepository;

    @Mock
    private OutletRepository outletRepository;

    @Mock
    private ProductApiClient productApiClient;

    @Mock
    private ServiceFunctions serviceFunctions;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerOrderServiceImpl customerOrderService;

    private CustomerOrderRequestDTO customerOrderRequestDTO;
    private CustomerOrder customerOrder;
    private Outlet outlet;

    @BeforeEach
    void setUp() {
        // Setup test outlet
        outlet = new Outlet();
        outlet.setOutletId(1);
        outlet.setOutletName("Test Outlet");

        // Setup test request DTO
        customerOrderRequestDTO = new CustomerOrderRequestDTO();
        customerOrderRequestDTO.setOutletId(1);
        customerOrderRequestDTO.setStatus(CustomerOrderStatus.Confirmed);
        customerOrderRequestDTO.setCustomerName("John Doe");
        customerOrderRequestDTO.setCustomerPhone("123456789");
        customerOrderRequestDTO.setItems(Arrays.asList(
                new CustomerOrderItemDTO(1, "P001", 2, 100.0, 0.0),
                new CustomerOrderItemDTO(2, "P002", 3, 150.0, 5.0)
        ));

        // Setup test customer order
        customerOrder = new CustomerOrder();
        customerOrder.setCusOrderID(1);
        customerOrder.setStatus(CustomerOrderStatus.Confirmed);
        customerOrder.setCustomerName("John Doe");
        customerOrder.setCustomerPhone("123456789");
        customerOrder.setOutlet(outlet);
        customerOrder.setOrderDate(new Date());

        // Setup test order items
        CustomerOrderItem item1 = new CustomerOrderItem();
        item1.setCusOrderItemId(1);
        item1.setProductId(1);
        item1.setQuantity(2);
        item1.setDiscountPerUnit(0.0);

        CustomerOrderItem item2 = new CustomerOrderItem();
        item2.setCusOrderItemId(2);
        item2.setProductId(2);
        item2.setQuantity(3);
        item2.setDiscountPerUnit(5.0);

        customerOrder.setCustomerOrderItem(Arrays.asList(item1, item2));
    }

    @Test
    void saveCusOrder_ShouldThrowException_WhenOutletNotFound() {
        // Arrange
        when(outletRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                customerOrderService.saveCusOrder(customerOrderRequestDTO)
        );
        verify(outletRepository, times(1)).findById(1);
        verify(customerOrderRepository, never()).save(any());
    }

    @Test
    void findAllByOutletId_ShouldReturnOrders_WhenOrdersExist() {
        // Arrange
        when(customerOrderRepository.findAll()).thenReturn(Arrays.asList(customerOrder));

        // Act
        List<CusOrderResponseDTO> result = customerOrderService.findAllByOutletId(1);

        // Assert
        assertEquals(1, result.size());
        CusOrderResponseDTO response = result.get(0);
        assertEquals(1, response.getCusOrderID());
        assertEquals("1", response.getOutletID());
        assertEquals("Test Outlet", response.getOutletName());
        assertEquals("John Doe", response.getCustomerName());
        verify(customerOrderRepository, times(1)).findAll();
    }

    @Test
    void findAllByOutletId_ShouldReturnEmptyList_WhenNoOrdersExist() {
        // Arrange
        when(customerOrderRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<CusOrderResponseDTO> result = customerOrderService.findAllByOutletId(1);

        // Assert
        assertTrue(result.isEmpty());
        verify(customerOrderRepository, times(1)).findAll();
    }

    @Test
    void findAllByOutletIdAndDate_ShouldReturnOrders_WhenOrdersExistForDate() {
        // Arrange
        Date testDate = new Date();
        when(serviceFunctions.makeDate(anyString())).thenReturn(testDate);
        when(customerOrderRepository.findAllByOutletAndDate(anyInt(), any(Date.class)))
                .thenReturn(Arrays.asList(customerOrder));

        // Act
        List<CusOrderResponseDTO> result = customerOrderService.findAllByOutletIdAndDate(1, "2025-04-05");

        // Assert
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getCustomerName());
        verify(serviceFunctions, times(1)).makeDate("2025-04-05");
    }

    @Test
    void findAllByOutletIdAndMonthAndDate_ShouldReturnOrders_WhenOrdersExistForMonth() {
        // Arrange
        when(customerOrderRepository.findAllByOutletAndYearAndMonth(anyInt(), anyInt(), anyInt()))
                .thenReturn(Arrays.asList(customerOrder));

        // Act
        List<CusOrderResponseDTO> result = customerOrderService.findAllByOutletIdAndMonthAndDate(1, 2025, 4);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Outlet", result.get(0).getOutletName());
        verify(customerOrderRepository, times(1))
                .findAllByOutletAndYearAndMonth(1, 2025, 4);
    }

    @Test
    void getCusOrderItems_ShouldHandleApiErrorGracefully() {
        // Arrange
        when(cusOrderItemRepository.getCustomerOrderItemsByCustomerOrder_CusOrderID(anyInt()))
                .thenReturn(customerOrder.getCustomerOrderItem());
        when(customerOrderRepository.findById(anyInt())).thenReturn(Optional.of(customerOrder));
        when(productApiClient.getPriceListByDateAndProductIdList(any(RequestDateAndPriceListDTO.class)))
                .thenReturn(ResponseEntity.internalServerError().build());

        // Act
        List<CustomerOrderItemDTO> result = customerOrderService.getCusOrderItems(1);

        // Assert
        assertTrue(result.isEmpty());
    }
}