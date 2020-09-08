package com.java.migros.dto;

import java.util.Set;

import com.java.migros.entity.OrderStatus;

import lombok.Data;

@Data
public class OrderDto {

	private Long id;
	
	private Long customerId;
	
	private String deliveryAddress;
	
	private String deliveryDate;

	private Set<LineItemDto> lineItems;

	private Double orderTotal;
	
	private OrderStatus orderStatus;

	public OrderDto() {
		super();
	}

	public OrderDto(Long id, Long customerId, String deliveryAddress, String deliveryDate, Set<LineItemDto> lineItems,
			Double orderTotal, OrderStatus orderStatus) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.deliveryAddress = deliveryAddress;
		this.deliveryDate = deliveryDate;
		this.lineItems = lineItems;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
	}

}
