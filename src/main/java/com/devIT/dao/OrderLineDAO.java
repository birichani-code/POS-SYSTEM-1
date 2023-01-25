package com.devIT.dao;

import java.util.List;

import com.devIT.entity.OrderLine;

public interface OrderLineDAO {
	
	public List<OrderLine> getOrderLines();
	
	public void saveOrderLine(OrderLine theOrderLine);
	
	public List<OrderLine> getOrderLineByOrderHeaderID(int orderHeaderId);

	public void deleteOrderLine(int theId);
}
