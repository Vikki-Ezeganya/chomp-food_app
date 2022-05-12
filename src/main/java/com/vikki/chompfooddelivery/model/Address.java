package com.vikki.chompfooddelivery.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "addresses")
public class Address implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = -1652694847619774329L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 10, nullable = false)
    private String addressId;

//    @Column(length = 10, nullable = true)
    @OneToOne
    @JoinColumn(name = "user_Id")
    private User user;

    @Column(length = 50, nullable = false)
    private String street;

    @Column(length = 15, nullable = false)
    private String city;

    @Column(length = 15, nullable = false)
    private String state;

    @Column(length = 15, nullable = false)
    private String country;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
