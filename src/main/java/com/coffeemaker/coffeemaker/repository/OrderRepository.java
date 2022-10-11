package com.coffeemaker.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coffeemaker.coffeemaker.models.Order;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {
	
	List<Order> findByType(String type);
	
}