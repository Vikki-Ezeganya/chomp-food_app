package com.vikki.chompfooddelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vikki.chompfooddelivery.dto.CartItemDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity(name = "cart")
public class Cart implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = -9012108108927011098L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String cartId;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

//    @Column(nullable = false)
    @OneToMany
    private List<CartItem> cartItems;

    @Column(nullable = false)
    private Integer cartQuantity;

    @Column(nullable = false)
    private Integer subTotal;

    public void saveCartItems (List<CartItemDto> items) {
        Cart cart = CartRepository.findMenuItemsByMenuId("");

        if(items != null ) {
            if (cartItems == null) {
                cartItems = new ArrayList<>();
            }

            items.stream().map(cartItemDto -> {
                Cart cart =
                CartItem cartItem = new CartItem();
                cartItem.setQuantity(cartItemDto.getQuantity());
                cartItem.setMenuItemId(cartItemDto.getMenuId());
                cartItem.setCart(cart);


                return cartItems.add(cartItem);
            });

        }


    }

}
