package com.devpeople.bapsim.domain.store.controller;

import com.devpeople.bapsim.domain.store.entity.Store;
import com.devpeople.bapsim.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // TODO 조회조건 추가 (ex. 사용자 식별자)
    @PostMapping("/productList")
    public List<Store> getProductList(){
        return storeService.getProductList();
    }
}
