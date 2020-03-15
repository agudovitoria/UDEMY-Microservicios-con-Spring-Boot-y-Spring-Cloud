package com.microservices.app.product.persistence.service;

import com.microservices.app.product.persistence.entity.Product;
import java.util.List;
import java.util.UUID;

public interface IProductService {
    public List<Product> FindAll();
    public Product FindById(UUID id);
}
