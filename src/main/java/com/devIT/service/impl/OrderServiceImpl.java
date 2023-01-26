package com.devIT.service.impl;

import com.devIT.entity.Order;
import com.devIT.entity.User;
import com.devIT.repository.OrderRepository;
import com.devIT.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;


	@Override
	public Order createOrder(User user) {
		return null;
	}

	/*public synchronized Order createOrder(
                User user) {
            order.setUser(user);
            order = orderRepository.save(order);

            return order;
        }*/
	public Order findById(Long id) {
		return orderRepository.findById(id).orElseThrow(()->new NullPointerException("Order Item"+ id+"Not Found"));
	}

}
