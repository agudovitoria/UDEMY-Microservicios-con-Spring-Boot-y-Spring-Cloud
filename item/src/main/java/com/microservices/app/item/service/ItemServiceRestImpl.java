package com.microservices.app.item.service;

import static java.util.Objects.isNull;

import com.microservices.app.item.clients.IProductRestClient;
import com.microservices.app.item.persistence.Item;
import com.microservices.app.item.persistence.Product;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Primary
public class ItemServiceRestImpl implements IItemService {

    private IProductRestClient restClient;

    @Override
    public List<Item> findAll() {
        List<Product> productList = restClient.findAll();

        return productList.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(UUID id) {
        Product product = restClient.findById(id);

        if (isNull(product)) {
            throw new RuntimeException("Not found product");
        }

        return new Item(product, 1);
    }
}
