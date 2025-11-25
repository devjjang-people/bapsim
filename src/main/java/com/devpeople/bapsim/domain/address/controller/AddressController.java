package com.devpeople.bapsim.domain.address.controller;

import com.devpeople.bapsim.domain.address.entity.Address;
import com.devpeople.bapsim.domain.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")

public class AddressController {
    private final AddressService addressService;

    // TODO 조회조건 추가 (ex. 사용자 식별자)
    @PostMapping("/list")
    public List<Address> getAddressList(){
        return addressService.getAddressList();
    }
}
