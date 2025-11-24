package com.devpeople.bapsim.domain.store.repository;

import com.devpeople.bapsim.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}

