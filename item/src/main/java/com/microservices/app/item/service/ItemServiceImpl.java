package com.microservices.app.item.service;

import static java.util.Objects.*;

import com.microservices.app.item.persistence.Item;
import com.microservices.app.item.persistence.Product;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements IItemService {

  private RestTemplate restTemplate;

  @Override
  public List<Item> findAll() {
    List<Product> productList =
        Arrays.asList(requireNonNull(restTemplate.getForObject("http://localhost:8001/products", Product[].class)));

    return productList.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
  }

  @Override
  public Item findById(UUID id) {
    Map<String, String> pathVariables = new HashMap<>();
    pathVariables.put("id", id.toString());
    Product product =
        restTemplate.getForObject("http://localhost:8001/products/{id}", Product.class, pathVariables);

    if (isNull(product)) {
      throw new RuntimeException("Not found product");
    }

    return new Item(product, 1);
  }
}
