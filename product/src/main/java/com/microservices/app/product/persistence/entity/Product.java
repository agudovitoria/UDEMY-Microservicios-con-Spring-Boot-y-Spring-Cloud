package com.microservices.app.product.persistence.entity;

import com.microservices.app.product.persistence.constant.IdGeneration;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "products")
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(generator = IdGeneration.GENERATOR)
    @GenericGenerator(name = IdGeneration.NAME, strategy = IdGeneration.STRATEGY)
    private UUID id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column(name="created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name="updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
}
