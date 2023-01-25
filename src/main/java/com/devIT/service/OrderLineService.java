package com.devIT.service;

import java.util.List;

import com.devIT.dto.OrderLineDTO;

public interface OrderLineService {

	public List<OrderLineDTO> getOrderLines();

	public void saveOrderLine(OrderLineDTO theOrderLine);

	List<OrderLineDTO> getOrderLinesbyOrderHeaderID(int orderHeaderID);
	
	public void deleteOrderLine(int theId);
	
	
}
