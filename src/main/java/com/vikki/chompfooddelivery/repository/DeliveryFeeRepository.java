package com.vikki.chompfooddelivery.repository;

import com.vikki.chompfooddelivery.model.DeliveryFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryFeeRepository extends JpaRepository<DeliveryFee, Integer> {
    DeliveryFee findByAmount(Integer amount);
}
