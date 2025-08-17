package com.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.PolicyCatalogue;

@Repository
public interface PolicyCatalogueRepo extends CrudRepository<PolicyCatalogue, Long>{
    
}
