package com.example.model;

import java.util.List;

import javax.persistence.ElementCollection;

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
public class OrderDetailsResponse {
	private Long orderId;
    private Long userId;
    private Boolean isPaymentDone;
    private Double totalAmountPaid;
    private List<Long> policyIds;
}
