package com.example.service;

import com.example.constants.UriConstants;
import com.example.entity.PolicyCatalogue;
import com.example.model.AllItemsInCartResponse;
import com.example.model.CreateOrderFromCartRequest;
import com.example.model.CreateOrderFromCartResponse;
import com.example.model.PolicyDetailsOfCartItemResponse;
import com.example.repo.PolicyCatalogueRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PolicyCatalogueServiceImpl implements PolicyCatalogueService {

    @Autowired
    private PolicyCatalogueRepo pRepo;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<PolicyCatalogue> getAllPolicies() {
        return (List<PolicyCatalogue>) pRepo.findAll();
    }

    @Override
    public PolicyCatalogue findPolicyById(Long id) {
        return pRepo.findById(id).isPresent() ? pRepo.findById(id).get() : null;
    }

    @Override
    public Boolean deletePolicy(Long id) {
        if (!pRepo.findById(id).isPresent())
            return false;
        pRepo.deleteById(id);
        return true;
    }

    @Override
    public void addPolicy(PolicyCatalogue policy) {
        pRepo.save(policy);
    }

    @Override
    public PolicyDetailsOfCartItemResponse getPolicyDetailsOfCartItems(Long id) {
        AllItemsInCartResponse cartItemsResponseBody = restTemplate.getForEntity(String.format(
                String.format(UriConstants.GET_ALL_ITEMS_FROM_CART_URL, id), id),
                AllItemsInCartResponse.class).getBody();
        List<PolicyCatalogue> listOfPolicyDetails = (List<PolicyCatalogue>) pRepo
                .findAllById(cartItemsResponseBody.getListOfPolicyIds());
        return PolicyDetailsOfCartItemResponse.builder().userId(id).listOfPolicyDetails(listOfPolicyDetails).build();
    }

    @Override
    public Boolean handleBuyPolicy(Long userId) {
        System.out.println("------- IN FUNCTION --------");
        AllItemsInCartResponse cartItemsResponseBody = restTemplate.getForEntity(String.format(
                String.format(UriConstants.GET_ALL_ITEMS_FROM_CART_URL, userId), userId),
                AllItemsInCartResponse.class).getBody();
        if (cartItemsResponseBody == null || cartItemsResponseBody.getListOfPolicyIds() == null ||
                cartItemsResponseBody.getListOfPolicyIds().isEmpty()) {
            return Boolean.FALSE;
        }
        restTemplate.postForEntity(UriConstants.CREATE_ORDER_FROM_CART_URL, CreateOrderFromCartRequest.builder()
                .userId(userId).isPaymentDone(Boolean.TRUE).build(), CreateOrderFromCartResponse.class);
        return Boolean.TRUE;
    }

}
