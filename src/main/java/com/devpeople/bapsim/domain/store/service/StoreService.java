package com.devpeople.bapsim.domain.store.service;

import com.devpeople.bapsim.domain.store.entity.Store;
import com.devpeople.bapsim.domain.store.repository.StoreRepository;
import com.devpeople.bapsim.global.exception.CustomException;
import com.devpeople.bapsim.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;

    public Store getStoreById(Long id) {

        return storeRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    }

    public List<Store> getStoreList() {

        return storeRepository.findAll();
    }

    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    public Store deleteStore(Long id) {

        Store store = storeRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
        store.setIsDeleted(true);

        return storeRepository.save(store);
    }
}
