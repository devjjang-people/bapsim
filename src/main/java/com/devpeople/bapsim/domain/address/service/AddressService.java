package com.devpeople.bapsim.domain.address.service;

import com.devpeople.bapsim.domain.address.entity.Address;
import com.devpeople.bapsim.domain.address.repository.AddressRepository;
import com.devpeople.bapsim.global.exception.AddressNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressService {
    private final AddressRepository addressRepository;

    public Address getAddressById(Long id) {

        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }

    public List<Address> getAddressList() {

        return addressRepository.findAll();
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address deleteAddress(Long id) {

        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
        address.setIsDeleted(true);

        return addressRepository.save(address);
    }
}
