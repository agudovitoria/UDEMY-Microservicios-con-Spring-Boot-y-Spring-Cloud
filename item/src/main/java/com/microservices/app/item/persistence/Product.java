package com.microservices.app.item.persistence;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
  private UUID id;
  private String name;
  private Double price;
  private Date createdAt;
  private Date updatedAt;
}
