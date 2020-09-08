package com.java.migros.entity;

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

@Entity(name = "BASKETS")
public class Basket extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "seq_basket", allocationSize = 1, initialValue = 1,sequenceName = "SEQ_BASKETS")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_basket")
    private Long id;
    
	@Column(name = "customer_id")
    private Long customerId;

    @Column(name = "shipment_price")
    private double shipmentPrice;

    @JsonManagedReference(value="basket-reference")
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    private Set<LineItem> lineItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(double shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    
    public Basket() {
		super();
	}

	public Basket(Long id, Long customerId, double shipmentPrice, Set<LineItem> lineItems) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.shipmentPrice = shipmentPrice;
		this.lineItems = lineItems;
	}
}
