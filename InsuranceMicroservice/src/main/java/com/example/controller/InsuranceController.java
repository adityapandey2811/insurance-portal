package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.PolicyCatalogue;

import com.example.service.PolicyCatalogueService;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private PolicyCatalogueService service;
    
    @PostMapping("/buyPolicy/{userId}")
    public ResponseEntity<Object> buyPolicy(@PathVariable final long userId){
    	System.out.println("UserId:- " + userId);
        service.handleBuyPolicy(userId);
        return ResponseEntity.ok().headers(new HttpHeaders()).body("DONE !!");
    }

    @GetMapping("/getAllPolicies")
    public ResponseEntity<Object> getAllPolicies(){
    	System.out.println("------ CHECKING ------");
        HttpHeaders header = new HttpHeaders();
        return ResponseEntity.ok()
        .headers(header)
        .body(service.getAllPolicies());
    }

    @PostMapping("/addPolicy")
    public ResponseEntity<Object> addPolicy(@RequestBody PolicyCatalogue policy) {
        service.addPolicy(policy);
        return ResponseEntity.ok()
                .body("Policy Added");
    }

    @DeleteMapping("/deletepolicy/{id}")
    public ResponseEntity<Object> deletePolicy(@PathVariable("id") Long id) {
        return Boolean.TRUE.equals(service.deletePolicy(id)) ? ResponseEntity.ok()
                .body("Deleted")
                : ResponseEntity.ok()
                        .body("User Not Found");
    }

    @GetMapping("/getpolicybyid/{id}")
    public ResponseEntity<Object> findPolicyById(@PathVariable("id") Long id) {
        HttpHeaders header = new HttpHeaders();
        return ResponseEntity.ok()
                .headers(header)
                .body(service.findPolicyById(id));
    }
    
    @GetMapping("/getPolicyDetailsOfCartItems/{userId}")
    public ResponseEntity<Object> getPolicyDetailsOfCartItem(@PathVariable final long userId){
    	HttpHeaders header = new HttpHeaders();
        return ResponseEntity.ok().headers(header).body(service.getPolicyDetailsOfCartItems(userId));
    }

}