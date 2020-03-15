package com.microservices.app.product.persistence.service;

import com.microservices.app.product.persistence.entity.Product;
import com.microservices.app.product.persistence.repository.IProductRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements IProductService {

    private IProductRepository repository;

    public ProductServiceImpl(IProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional
    public Product findById(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
