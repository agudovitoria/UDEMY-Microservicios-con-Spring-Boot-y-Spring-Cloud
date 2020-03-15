package com.microservices.app.item.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    Product product;
    Integer amount;

    public Double getTotal() {
        return product.getPrice() * amount;
    }
}
