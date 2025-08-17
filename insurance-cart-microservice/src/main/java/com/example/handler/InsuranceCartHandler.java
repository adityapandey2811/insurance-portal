package com.example.handler;

import java.util.List;

import com.example.entity.Orders;
import com.example.model.*;

public interface InsuranceCartHandler {
    public abstract AddPolicyToCartResponse addPolicyToCart(final AddPolicyToCartRequest addPolicyToCartRequest);

    public abstract DeletePolicyFromCartResponse deletePolicyFromCart(
            final DeletePolicyFromCartRequest deletePolicyFromCartRequest);

    public abstract CreateOrderFromCartResponse createOrderFromCart(
            final CreateOrderFromCartRequest createOrderRequest);

    public abstract void clearCartForUser(final Long userId);

    public abstract AllItemsInCartResponse getAllItemsFromCart(final Long userId);
    
    public abstract List<OrderDetailsResponse> getOrderDetails(final Long userId);
}
