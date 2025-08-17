package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Discount;
import com.example.repo.DiscountRepo;

@Service
public class DiscountDaoImpl implements DiscountDao {

	@Autowired
	private DiscountRepo dRepo;

	@Override
	public void addDiscount(Discount discount) {
		dRepo.save(discount);
	}

	@Override
	public List<Discount> getDiscountByPolicyId() {
		return (List<Discount>) dRepo.findAll();
	}

}
