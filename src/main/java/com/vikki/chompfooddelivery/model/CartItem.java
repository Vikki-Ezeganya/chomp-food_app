package com.vikki.chompfooddelivery.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "cartItems")
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("quantity")
    private Integer quantity;

//    private MenuItem menuItem;
    @JsonProperty("menuId")
    private String menuItemId;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

}
