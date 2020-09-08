package com.java.migros.entity;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.ToString;

@ToString
@Entity(name = "ORDERS")
public class Order extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_order", sequenceName = "SEQ_ORDERS", initialValue = 1, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
	private Long id;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@JsonManagedReference
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
	private Set<LineItem> lineItems = new HashSet<>();

	@Column(name = "delivery_date")
	private ZonedDateTime deliveryDate;

	@Column(name = "order_total")
	private Double orderTotal;

	@Column(name = "delivery_address")
	private String deliveryAddress;
	
	@Column(name = "order_status")
	private OrderStatus orderStatus;

	@Override public Long getId() {
		return id;
	}

	@Override public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public ZonedDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(ZonedDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
