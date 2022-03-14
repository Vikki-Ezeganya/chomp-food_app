package com.vikki.chompfooddelivery.model;

import javax.persistence.*;

@Entity(name = "cartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    @Column(nullable = false)
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    @Column(nullable = false)
    private Cart cart;



}
