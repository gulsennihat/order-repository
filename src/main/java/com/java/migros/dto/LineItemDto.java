package com.java.migros.dto;

import lombok.Data;

@Data
public class LineItemDto {

	private Long id;

	private Long productId;

	private int amount;

	private double price;

	public LineItemDto(Long id, Long productId, int amount, double price) {
		super();
		this.id = id;
		this.productId = productId;
		this.amount = amount;
		this.price = price;
	}

	public LineItemDto() {
		super();
	}
	
	
}
