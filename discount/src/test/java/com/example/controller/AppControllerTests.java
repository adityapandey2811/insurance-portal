package com.example.controller;

import com.example.controller.AppController;
import com.example.dao.DiscountDao;
import com.example.entity.Discount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppControllerTests {

    @InjectMocks
    private AppController appController;

    @Mock
    private DiscountDao discountDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addDiscountTest() throws URISyntaxException {
        Discount discount = Discount.builder()
                .policyId(1L)
                .value(0.2)
                .build();

        doNothing().when(discountDao).addDiscount(any(Discount.class));

        ResponseEntity<String> response = appController.addDiscount(discount);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        HttpHeaders headers = response.getHeaders();
        assertTrue(headers.containsKey("Policy-Id"));
        assertEquals("Discount created successfully", response.getBody());
    }

    @Test
    public void showDiscountByPolicyIdTest() {
        List<Discount> discounts = Collections.singletonList(new Discount());
        Mockito.when(discountDao.getDiscountByPolicyId()).thenReturn(discounts);
        ResponseEntity<Object> response = appController.showDiscountByPolicyId();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(discounts, response.getBody());
    }

}
