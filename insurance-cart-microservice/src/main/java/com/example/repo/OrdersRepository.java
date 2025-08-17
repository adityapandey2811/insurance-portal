package com.example.repo;

import com.example.entity.CartItems;
import com.example.entity.Orders;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Integer> {
	@Modifying
    @Query("SELECT c FROM Orders c WHERE c.userId = :userId")
    public abstract List<Orders> getOrdersByUserId(@Param("userId") final Long userId);
}
