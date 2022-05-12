package com.vikki.chompfooddelivery.repository;

import com.vikki.chompfooddelivery.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDetailsRepository extends JpaRepository<Address,Long> {
    Address findByAddressId(String addressId);
    Address findByUserId(Long id);
}
