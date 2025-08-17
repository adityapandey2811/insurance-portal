package com.example.dao;

import com.example.entity.Orders;
import com.example.repo.OrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class OrdersDaoImplTests {
    @InjectMocks
    private OrdersDao ordersDao = new OrdersDaoImpl();
    @Mock
    private OrdersRepository ordersRepository;

    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addOrderTest() {
        Orders order = Orders.builder()
                .userId(1L)
                .isPaymentDone(true)
                .build();
        ordersDao.addOrder(order);
        verify(ordersRepository, times(1)).save(order);
    }
}
