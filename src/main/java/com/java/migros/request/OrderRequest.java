package com.java.migros.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderRequest {

	private Long id;

	private Long customerId;

	private Long basketId;

	private String deliveryAddress;

	private String deliveryDate;

	public OrderRequest(Long customerId, Long basketId, String deliveryAddress, String deliveryDate) {
		super();
		this.customerId = customerId;
		this.basketId = basketId;
		this.deliveryAddress = deliveryAddress;
		this.deliveryDate = deliveryDate;
	}

	public OrderRequest() {
		super();
	}

}
