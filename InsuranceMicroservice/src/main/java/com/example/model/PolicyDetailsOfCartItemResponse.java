package com.example.model;

import java.util.List;

import com.example.entity.PolicyCatalogue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PolicyDetailsOfCartItemResponse {
	private Long userId;
	private List<PolicyCatalogue> listOfPolicyDetails;
}
