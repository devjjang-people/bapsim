package com.devpeople.bapsim.domain.store.service;

import com.devpeople.bapsim.domain.store.entity.Store;
import com.devpeople.bapsim.domain.store.repository.StoreRepository;
import com.devpeople.bapsim.global.exception.StoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;

    public Store getProductById(Integer id) {

        return storeRepository.findById(id).orElseThrow(() -> new StoreNotFoundException(id));
    }

    public List<Store> getProductList() {

        return storeRepository.findAll();
    }

    public Store saveProduct(Store store) {
        return storeRepository.save(store);
    }

    public Store deleteAddress(Integer id) {

        Store store = storeRepository.findById(id).orElseThrow(() -> new StoreNotFoundException(id));
        store.setIsDeleted(true);

        return storeRepository.save(store);
    }
}
