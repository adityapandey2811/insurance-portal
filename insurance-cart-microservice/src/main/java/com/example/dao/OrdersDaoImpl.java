package com.example.dao;

import com.example.entity.Orders;
import com.example.repo.OrdersRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersDaoImpl implements OrdersDao {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public void addOrder(Orders order) {
        ordersRepository.save(order);
    }

	@Override
	public List<Orders> getOrderForUser(Long userId) {
		return ordersRepository.getOrdersByUserId(userId);
	}
}
