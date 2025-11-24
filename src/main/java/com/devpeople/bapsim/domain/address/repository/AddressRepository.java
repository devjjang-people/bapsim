package com.devpeople.bapsim.domain.address.repository;

import com.devpeople.bapsim.domain.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

