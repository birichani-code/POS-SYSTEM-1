package com.devIT.controller;
import java.util.ArrayList;
import java.util.List;

import com.devIT.dto.OrderHeaderDTO;
import com.devIT.service.OrderHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
	
	
	@Autowired
	private OrderHeaderService orderHeaderService;
	
	
	@GetMapping("/")
	public ModelAndView showHome1() {
		

		

		List<OrderHeaderDTO> theOrderHeader=orderHeaderService.getAllOrders();
		List<String> orderStatusList = new ArrayList();
		orderStatusList.clear();
		orderStatusList.add("Complete");

		ModelAndView model1 = new ModelAndView();
		model1.setViewName("index");
		
		model1.addObject("orderheader",theOrderHeader);
		model1.addObject("orderStatusList", orderStatusList);
		
		
	

		
		return model1;
	}
	@GetMapping("/create")
	public String createOrder() {
		
	

		return "redirect:/homeController/load";
	}
	

}
