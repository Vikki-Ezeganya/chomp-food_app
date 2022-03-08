package com.vikki.chompfooddelivery.repository;

import com.vikki.chompfooddelivery.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Long> {

}

