package com.java.migros.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.ToString;

@ToString
@Entity(name = "LINE_ITEMS")
public class LineItem extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_li", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_LI")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_li")
	private Long id;

	@JsonBackReference(value = "basket-reference")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Basket basket;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Order order;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "amount")
	private int amount;

	@Column(name = "price")
	private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public LineItem(Long productId, int amount, double price) {
		super();
		this.productId = productId;
		this.amount = amount;
		this.price = price;
	}

	public LineItem() {
		super();
	}
	
	
}
