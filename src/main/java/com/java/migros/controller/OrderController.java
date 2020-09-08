package com.java.migros.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.migros.dto.OrderDto;
import com.java.migros.request.OrderRequest;
import com.java.migros.service.OrderServiceImpl;
import com.java.migros.utils.ApiResponse;


@RestController
@RequestMapping("order")
	public class OrderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private OrderServiceImpl orderService;

	@PostMapping("/create-order")
	public ApiResponse<OrderDto> createOrder(@RequestBody OrderRequest request) {
		logger.info("started create order. OrderRequest: " + request);
		return orderService.createOrder(request);
	}

}
