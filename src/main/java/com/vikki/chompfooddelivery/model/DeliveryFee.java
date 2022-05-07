package com.vikki.chompfooddelivery.model;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Data
@Entity(name = "delivery_fee")
public class DeliveryFee extends AbstractPersistable<Long> {
    private Integer amount;

}
