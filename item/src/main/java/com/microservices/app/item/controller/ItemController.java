package com.microservices.app.item.controller;

import com.microservices.app.item.persistence.Item;
import com.microservices.app.item.service.IItemService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("items")
public class ItemController {
    @Qualifier("feignProductClient")
    private IItemService service;

    @GetMapping()
    public List<Item> getAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Item getById(@PathVariable UUID id) {
        return service.findById(id);
    }
}
