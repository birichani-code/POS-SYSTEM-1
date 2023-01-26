package com.devIT.service;
import com.devIT.entity.Order;
import com.devIT.entity.User;

public interface OrderService {
	Order createOrder(

			User user);
	
	Order findById(Long id);
}
