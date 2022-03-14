package com.vikki.chompfooddelivery.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @Column(nullable = false)
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(nullable = false)
    @OneToMany(mappedBy = "cart", cascade=CascadeType.ALL)
    private List<CartItem> cartItem;

    @Column(nullable = false)
    private Integer cartQuantity;

    @Column(nullable = false)
    private Integer subTotal;

}
