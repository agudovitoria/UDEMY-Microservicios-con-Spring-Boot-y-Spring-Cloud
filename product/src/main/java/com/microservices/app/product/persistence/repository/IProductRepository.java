package com.microservices.app.product.persistence.repository;

import com.microservices.app.product.persistence.entity.Product;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends CrudRepository<Product, UUID> {}
