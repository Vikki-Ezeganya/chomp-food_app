package com.vikki.chompfooddelivery.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "delivery_fee")
public class DeliveryFee implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 3603270164218206581L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "amount", nullable=false)
    private Integer amount;

}
