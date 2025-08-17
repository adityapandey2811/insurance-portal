package com.example.handler;

import com.example.dao.CartItemsDao;
import com.example.dao.OrdersDao;
import com.example.entity.CartItems;
import com.example.entity.Orders;
import com.example.model.AllItemsInCartResponse;
import com.example.model.CreateOrderFromCartRequest;
import com.example.model.AddPolicyToCartRequest;
import com.example.model.AddPolicyToCartResponse;
import com.example.model.CreateOrderFromCartResponse;
import com.example.model.DeletePolicyFromCartRequest;
import com.example.model.DeletePolicyFromCartResponse;
import com.example.model.OrderDetailsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceCartHandlerImpl implements InsuranceCartHandler {
    @Autowired
    private CartItemsDao cartDao;
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public AddPolicyToCartResponse addPolicyToCart(final AddPolicyToCartRequest addPolicyToCartRequest) {
        cartDao.saveItemToCart(CartItems.builder()
                .userId(addPolicyToCartRequest.getUserId())
                .policyId(addPolicyToCartRequest.getPolicyId())
                .build());
        return AddPolicyToCartResponse.builder()
                .isPolicyAddedSuccessfully(Boolean.TRUE).build();
    }

    @Override
    public DeletePolicyFromCartResponse deletePolicyFromCart(
            final DeletePolicyFromCartRequest deletePolicyFromCartRequest) {
        cartDao.deleteItemFromCart(deletePolicyFromCartRequest.getUserId(), deletePolicyFromCartRequest.getPolicyId());
        return DeletePolicyFromCartResponse.builder().isPolicyDeleteSuccessfully(true).build();
    }

    @Override
    public CreateOrderFromCartResponse createOrderFromCart(final CreateOrderFromCartRequest createOrderRequest) {
        List<CartItems> listOfCartItems = cartDao.getAllItemsForUser(createOrderRequest.getUserId());
        if (listOfCartItems.isEmpty()) {
            return CreateOrderFromCartResponse.builder().isOrderCreated(false).build();
        }
        ordersDao.addOrder(Orders.builder()
                .userId(createOrderRequest.getUserId())
                .isPaymentDone(createOrderRequest.getIsPaymentDone())
                .totalAmountPaid(createOrderRequest.getTotalAmountPaid())
                .policyIds(listOfCartItems
                        .stream()
                        .map(CartItems::getPolicyId)
                        .collect(Collectors.toList()))
                .build());
        cartDao.removeListOfItems(listOfCartItems);
        return CreateOrderFromCartResponse.builder().isOrderCreated(true).build();
    }

    @Override
    @Transactional
    public void clearCartForUser(final Long userId) {
        cartDao.clearCartForUser(userId);
    }

    @Override
    public AllItemsInCartResponse getAllItemsFromCart(final Long userId) {
        return AllItemsInCartResponse.builder()
                .userId(userId)
                .listOfPolicyIds(cartDao.getAllItemsForUser(userId)
                        .stream().map(CartItems::getPolicyId)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<OrderDetailsResponse> getOrderDetails(final Long userId) {
        List<Orders> ordersList = ordersDao.getOrderForUser(userId);
        return ordersList.stream().map(order -> {
            return OrderDetailsResponse.builder()
                    .isPaymentDone(order.getIsPaymentDone())
                    .totalAmountPaid(order.getTotalAmountPaid())
                    .orderId(order.getOrderId())
                    .policyIds(order.getPolicyIds())
                    .userId(order.getUserId())
                    .build();
        }).collect(Collectors.toList());
    }
}
