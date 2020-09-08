package com.java.migros.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class BasketDto {

	private Long id;

	private Long customerId;

	private double shipmentPrice;

	private Set<LineItemDto> lineItems = new HashSet<>();

}
