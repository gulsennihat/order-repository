package com.java.migros.service;

import com.java.migros.dto.OrderDto;
import com.java.migros.request.OrderRequest;
import com.java.migros.utils.ApiResponse;

public interface OrderService {

	ApiResponse<OrderDto> createOrder(OrderRequest request);
	
}
