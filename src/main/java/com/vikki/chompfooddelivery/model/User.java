package com.vikki.chompfooddelivery.model;

import com.vikki.chompfooddelivery.enums.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "users")
public class User implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 6044957531319472967L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length=20)
    private String firstName;

    @Column(nullable = false, length=20)
    private String lastName;

    @Column(nullable = false, length=20)
    private String phoneNumber;

    @Column(nullable = false, length=50)
    private String email;

    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private String encryptedPassword;

    private String emailVerificationToken;

    @Column(nullable = false)
    private Boolean emailVerificationStatus = false;

//    @OneToOne(mappedBy="userDetails", cascade = CascadeType.ALL)
//    private Address address;


}
