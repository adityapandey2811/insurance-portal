package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.example.entity.PolicyCatalogue;
import com.example.service.PolicyCatalogueService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import com.example.model.AllItemsInCartResponse;
import com.example.model.CreateOrderFromCartRequest;
import com.example.model.CreateOrderFromCartResponse;

@ExtendWith(MockitoExtension.class)
public class InsuranceControllerTests {
    @InjectMocks
    private InsuranceController insuranceController;
    @Mock
    private PolicyCatalogueService policyCatalogueService;

    @Test
    public void buyPolicyTest() {
        Long userId = 1L;
        ResponseEntity<Object> result = insuranceController.buyPolicy(userId);
        assertNotNull(result);
        assertEquals("DONE !!", result.getBody());
    }

    @Test
    public void getAllPoliciesTest() {
        List<PolicyCatalogue> policyList = new ArrayList<>();
        when(policyCatalogueService.getAllPolicies()).thenReturn(policyList);
        ResponseEntity<Object> result = insuranceController.getAllPolicies();
        assertNotNull(result);
        assertEquals(policyList, result.getBody());
    }

    @Test
    public void addPolicyTest() {
        PolicyCatalogue policy = new PolicyCatalogue();
        doNothing().when(policyCatalogueService).addPolicy(policy);
        ResponseEntity<Object> result = insuranceController.addPolicy(policy);
        assertNotNull(result);
        assertEquals("Policy Added", result.getBody());
    }


    @Test
    public void deletePolicyTest() {
        Long policyId = 1L;
        when(policyCatalogueService.deletePolicy(policyId)).thenReturn(true);
        ResponseEntity<Object> result = insuranceController.deletePolicy(policyId);
        assertNotNull(result);
        assertEquals("Deleted", result.getBody());
    }

    @Test
    public void deletePolicy_NotFoundTest() {
        Long policyId = 1L;
        when(policyCatalogueService.deletePolicy(policyId)).thenReturn(false);
        ResponseEntity<Object> result = insuranceController.deletePolicy(policyId);
        assertNotNull(result);
        assertEquals("User Not Found", result.getBody());
    }

    @Test
    public void findPolicyByIdTest() {
        Long policyId = 1L;
        when(policyCatalogueService.findPolicyById(policyId)).thenReturn(PolicyCatalogue.builder().build());
        ResponseEntity<Object> result = insuranceController.findPolicyById(policyId);
        assertNotNull(result);
        assertTrue(result.getHeaders().isEmpty());
        assertNotNull(result.getBody());
    }
}
