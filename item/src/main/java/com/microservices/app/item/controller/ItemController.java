package com.microservices.app.item.controller;

import com.microservices.app.item.persistence.Item;
import com.microservices.app.item.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/")
@Slf4j
public class ItemController {

    private IItemService service;

    @GetMapping()
    @HystrixCommand(fallbackMethod = "getAllFallback")
    public List<Item> getAll() {
        log.debug("ItemController.getAll()");
        return service.findAll();
    }

    public List<Item> getAllFallback() {
        log.debug("ItemController.getAll() fallback");
        return Collections.emptyList();
    }

    @GetMapping("{id}")
    public Item getById(@PathVariable UUID id) {
        log.debug(String.format("ItemController.getById(%s) fallback", id.toString()));
        return service.findById(id);
    }
}
