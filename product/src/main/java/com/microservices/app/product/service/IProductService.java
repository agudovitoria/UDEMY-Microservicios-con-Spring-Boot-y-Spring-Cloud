package com.microservices.app.product.service;

import com.microservices.app.product.persistence.entity.Product;
import java.util.List;
import java.util.UUID;

public interface IProductService {
    List<Product> findAll();
    Product findById(UUID id);
}
