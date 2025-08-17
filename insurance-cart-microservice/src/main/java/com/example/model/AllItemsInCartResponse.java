package com.example.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AllItemsInCartResponse {
    private Long userId;
    private List<Long> listOfPolicyIds;
}
