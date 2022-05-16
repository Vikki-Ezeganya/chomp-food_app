package com.vikki.chompfooddelivery.repository;

import com.vikki.chompfooddelivery.model.DeliveryFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DeliveryFeeRepository extends JpaRepository<DeliveryFee, Integer> {
    DeliveryFee findByAmount(Integer amount);
    DeliveryFee deleteAllByAmount(Integer amount);
}
