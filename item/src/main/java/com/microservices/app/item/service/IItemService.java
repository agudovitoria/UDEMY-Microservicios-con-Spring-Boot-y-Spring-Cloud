package com.microservices.app.item.service;

import com.microservices.app.item.persistence.Item;
import java.util.List;
import java.util.UUID;

public interface IItemService {
    List<Item> findAll();
    Item findById(UUID id);
}
