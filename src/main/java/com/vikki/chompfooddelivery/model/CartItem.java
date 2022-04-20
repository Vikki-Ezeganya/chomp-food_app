package com.vikki.chompfooddelivery.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "cart_item")
public class CartItem implements Serializable {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = -3322472324407808867L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String cartItemId;

    @ManyToOne()
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "menuItem_id")
    private MenuItem menuItem;

    @Column(nullable = false)
    private Integer quantity;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", user=" + user +
                ", menuItem=" + menuItem +
                ", quantity=" + quantity +
                '}';
    }
}
