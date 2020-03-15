package com.microservices.app.item.clients;

import com.microservices.app.item.persistence.Product;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="product-service")
@RequestMapping("products")
public interface IProductRestClient {
    @GetMapping()
    List<Product> findAll();

    @GetMapping("{id}")
    Product findById(@PathVariable UUID id);
}
