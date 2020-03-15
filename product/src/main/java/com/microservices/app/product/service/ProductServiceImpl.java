package com.microservices.app.product.service;

import com.microservices.app.product.persistence.entity.Product;
import com.microservices.app.product.persistence.repository.IProductRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private IProductRepository repository;

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
