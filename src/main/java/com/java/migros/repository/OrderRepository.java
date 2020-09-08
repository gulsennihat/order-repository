package com.java.migros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.migros.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
