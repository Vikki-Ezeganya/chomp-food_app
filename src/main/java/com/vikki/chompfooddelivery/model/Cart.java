package com.vikki.chompfooddelivery.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "cartItem")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private Integer quantity;
}
