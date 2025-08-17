package com.example.service;

import java.util.List;

import com.example.entity.PolicyCatalogue;
import com.example.model.PolicyDetailsOfCartItemResponse;

public interface PolicyCatalogueService {
    public void addPolicy(PolicyCatalogue policy);

    public Boolean deletePolicy(Long id);

    public List<PolicyCatalogue> getAllPolicies();

    public PolicyCatalogue findPolicyById(Long id);
    public PolicyDetailsOfCartItemResponse getPolicyDetailsOfCartItems(Long id);

	public Boolean handleBuyPolicy(Long userId);
}
