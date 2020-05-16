package com.hitesh.microservices.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.hitesh.microservices.inventory.models.Product;

public interface ProductRepository  extends CrudRepository<Product, Long> {
	   
}



