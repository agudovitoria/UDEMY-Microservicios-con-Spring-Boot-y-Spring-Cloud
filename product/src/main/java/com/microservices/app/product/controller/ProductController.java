package com.microservices.app.product.controller;

import com.microservices.app.product.persistence.entity.Product;
import com.microservices.app.product.service.IProductService;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class ProductController {

  private final IProductService service;

  public ProductController(IProductService service) {
    this.service = service;
  }

  @GetMapping()
  public List<Product> getAll() {
    log.debug("ProductController.getAll()");
    return service.findAll();
  }

  @GetMapping("{id}")
  public Product getById(@PathVariable UUID id) {
    log.debug(String.format("ProductController.getById(%s) fallback", id.toString()));
    return service.findById(id);
  }
}
