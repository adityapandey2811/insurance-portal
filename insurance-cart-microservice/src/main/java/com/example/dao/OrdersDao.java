package com.example.dao;


import java.util.List;

import com.example.entity.Orders;

public interface OrdersDao {
    public abstract void addOrder(final Orders order);
    public abstract List<Orders> getOrderForUser(final Long userId);
}
