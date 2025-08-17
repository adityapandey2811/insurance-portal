package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.entity.PolicyCatalogue;
import com.example.model.AllItemsInCartResponse;
import com.example.model.CreateOrderFromCartRequest;
import com.example.model.CreateOrderFromCartResponse;
import com.example.repo.PolicyCatalogueRepo;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class PolicyCatalogueServiceImplTests {
    @Mock
    private PolicyCatalogueRepo policyCatalogueRepo;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private PolicyCatalogueServiceImpl policyCatalogueService;
    
    @Test
    public void testBuyPolicy() {
    	Long userId = 1L;
    	List<Long> listOfPolicyId = new ArrayList<>();
    	listOfPolicyId.add(100L);
        ResponseEntity<AllItemsInCartResponse> cartItemsResponse = ResponseEntity.ok(AllItemsInCartResponse.builder().userId(userId).listOfPolicyIds(listOfPolicyId).build());
        when(restTemplate.getForEntity(anyString(), eq(AllItemsInCartResponse.class)))
                .thenReturn(cartItemsResponse);
        ResponseEntity<CreateOrderFromCartResponse> createOrderResponse = ResponseEntity.ok(CreateOrderFromCartResponse.builder().isOrderCreated(true).build());
        when(restTemplate.postForEntity(anyString(), any(CreateOrderFromCartRequest.class), eq(CreateOrderFromCartResponse.class)))
                .thenReturn(createOrderResponse);
        Object result = policyCatalogueService.handleBuyPolicy(userId);
        assertNotNull(result);
    }

    @Test
    public void getAllPoliciesTest() {
        List<PolicyCatalogue> policyList = new ArrayList<>();
        when(policyCatalogueRepo.findAll()).thenReturn(policyList);
        List<PolicyCatalogue> result = policyCatalogueService.getAllPolicies();
        assertEquals(0, result.size());
    }

    @Test
    public void findPolicyByIdTest() {
        Long policyId = 1L;
        PolicyCatalogue policy = new PolicyCatalogue();
        policy.setPolicyId(policyId);
        Optional<PolicyCatalogue> optionalPolicy = Optional.of(policy);
        when(policyCatalogueRepo.findById(policyId)).thenReturn(optionalPolicy);
        PolicyCatalogue result = policyCatalogueService.findPolicyById(policyId);
        assertNotNull(result);
        assertEquals(policyId, result.getPolicyId());
    }

    @Test
    public void findPolicyById_NotFoundTest() {
        Long policyId = 1L;
        when(policyCatalogueRepo.findById(policyId)).thenReturn(Optional.empty());
        PolicyCatalogue result = policyCatalogueService.findPolicyById(policyId);
        assertNull(result);
    }

    @Test
    public void deletePolicyTest() {
        Long policyId = 1L;
        when(policyCatalogueRepo.findById(policyId)).thenReturn(Optional.of(new PolicyCatalogue()));
        Boolean result = policyCatalogueService.deletePolicy(policyId);
        assertTrue(result);
        verify(policyCatalogueRepo, times(1)).deleteById(policyId);
    }

    @Test
    public void deletePolicy_NotFoundTest() {
        Long policyId = 1L;
        when(policyCatalogueRepo.findById(policyId)).thenReturn(Optional.empty());
        Boolean result = policyCatalogueService.deletePolicy(policyId);
        assertFalse(result);
        verify(policyCatalogueRepo, never()).deleteById(policyId);
    }

    @Test
    public void addPolicyTest() {
        PolicyCatalogue policy = new PolicyCatalogue();
        policyCatalogueService.addPolicy(policy);
        verify(policyCatalogueRepo, times(1)).save(policy);
    }
}
