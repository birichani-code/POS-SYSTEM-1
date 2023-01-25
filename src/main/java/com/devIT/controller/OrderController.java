package com.devIT.controller;

import java.util.ArrayList;
import java.util.List;

import com.devIT.dto.CustomerDTO;
import com.devIT.dto.OrderHeaderDTO;
import com.devIT.dto.OrderLineDTO;
import com.devIT.dto.ProductDTO;
import com.devIT.service.CustomerService;
import com.devIT.service.OrderHeaderService;
import com.devIT.service.OrderLineService;
import com.devIT.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderController")
public class OrderController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderHeaderService orderHeaderService;

	@Autowired
	private OrderLineService orderLineService;

	@GetMapping("/load")
	public ModelAndView showHome(@ModelAttribute("customer") CustomerDTO theCustomer,
			@RequestParam(name = "orderHeaderNumber", required = false) String orderHeaderNumber) {

		ModelAndView model3 = new ModelAndView();

		OrderHeaderDTO oh = null;

		List<OrderLineDTO> theOrderLines;

		List<String> orderStatusList = new ArrayList();

		if (orderHeaderNumber == null) {

			String nextOrderNumber = orderHeaderService.getNextOrderHeaderNumber();
			oh = new OrderHeaderDTO();

			oh.setNumber(nextOrderNumber);

			orderStatusList.add("New");

		} else {
			oh = orderHeaderService.getOrderHeaderByNumber(orderHeaderNumber);
			orderStatusList.clear();
			orderStatusList.add("Proccessing");

			theOrderLines = orderLineService.getOrderLinesbyOrderHeaderID(oh.getOrderHeaderId());
			oh.setOrderLine(theOrderLines.toArray(new OrderLineDTO[0]));

		}

		List<String> orderTypeList = new ArrayList();
		orderTypeList.add("Sales Order");
		orderTypeList.add("Purchase Order");

		List<CustomerDTO> theCustomers = customerService.getCustomer();

		List<ProductDTO> theProducts = productService.getProducts();

		model3.setViewName("order");
		model3.addObject("formOrderHeader", oh);
		model3.addObject("orderTypeList", orderTypeList);
		model3.addObject("orderStatusList", orderStatusList);
		model3.addObject("productList", theProducts);
		model3.addObject("customers", theCustomers);

		return model3;
	}

	@PostMapping("/saveOrderHeader")
	public String saveOrderHeader(@ModelAttribute("formOrderHeader") OrderHeaderDTO oh, BindingResult result,
			ModelMap model) {

		orderHeaderService.saveOrderHeader(oh);

		return "redirect:/orderController/load?orderHeaderNumber=" + oh.getNumber();
	}

	@GetMapping("/delete")
	public String deleteOrderLine(@RequestParam("orderLineId") int theId,
			@RequestParam("orderNumber") String orderNumber) {

		// delete the product
		orderLineService.deleteOrderLine(theId);

		return "redirect:/orderController/load?orderHeaderNumber=" + orderNumber;
	}

	@GetMapping("/checkout")
	public String checkoutOrder(@RequestParam("orderNumber") String orderNumber) {

		List<String> orderStatusList = new ArrayList();
		orderStatusList.clear();
		orderStatusList.add("Complete");

		orderHeaderService.checkout(orderNumber);

		return "redirect:/orderController/load?orderHeaderNumber=" + orderNumber;
	}

}
