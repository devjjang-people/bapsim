package com.devpeople.bapsim.domain.address.service;

import com.devpeople.bapsim.domain.address.entity.Address;
import com.devpeople.bapsim.domain.address.repository.AddressRepository;
import com.devpeople.bapsim.global.exception.CustomException;
import com.devpeople.bapsim.global.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        address = Address.builder()
                .id(1L)
                .address1("서울시 강남구 테헤란로 123")
                .zipcode("06236")
                .isDeleted(false)
                .build();
    }

    @Test
    void getAddressById_정상조회() {
        // given
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        // when
        Address result = addressService.getAddressById(1L);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getAddress1()).isEqualTo("서울시 강남구 테헤란로 123");
        assertTrue(address.getZipcode().startsWith("06"));

        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void getAddressById_주소없음_예외발생() {
        // given
        when(addressRepository.findById(999L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> addressService.getAddressById(999L))
                .isInstanceOf(CustomException.class)
                .satisfies(ex -> {
                    CustomException ce = (CustomException) ex;
                    assertThat(ce.getErrorCode()).isEqualTo(ErrorCode.ADDRESS_NOT_FOUND);
                });
    }

    @Test
    void getAddressList_전체조회_성공() {
        // given
        List<Address> mockList = Arrays.asList(address, new Address());
        when(addressRepository.findAll()).thenReturn(mockList);

        // when
        List<Address> result = addressService.getAddressList();

        // then
        assertThat(result).hasSize(2);
        verify(addressRepository, times(1)).findAll();
    }

    void saveAddress_신규등록_성공() {
        // given
        Address newAddress = Address.builder()
                .address1("서울시 마포구 독막로 45")
                .zipcode("04109")
                .isDeleted(false)
                .build();

        when(addressRepository.save(any(Address.class))).thenReturn(newAddress);

        // when
        Address result = addressService.saveAddress(newAddress);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getAddress1()).isEqualTo("서울시 마포구 독막로 45");
        verify(addressRepository, times(1)).save(newAddress);
    }

    @Test
    void saveAddress_기존데이터_수정_성공() {
        // given
        Address existingAddress = Address.builder()
                .id(1L)
                .address1("서울시 강남구 테헤란로 123")
                .zipcode("06236")
                .isDeleted(false)
                .build();

        // 기존 address 수정
        existingAddress.setAddress1("서울시 강남구 역삼로 456");
        existingAddress.setZipcode("06123");

        when(addressRepository.save(any(Address.class))).thenReturn(existingAddress);

        // when
        Address result = addressService.saveAddress(existingAddress);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getAddress1()).isEqualTo("서울시 강남구 역삼로 456");
        assertThat(result.getZipcode()).isEqualTo("06123");

        // save 호출 검증
        verify(addressRepository, times(1)).save(existingAddress);
    }

    @Test
    void deleteAddress_정상삭제_성공() {
        // given
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        // when
        Address result = addressService.deleteAddress(1L);

        // then
        assertThat(result.getIsDeleted()).isTrue();
        verify(addressRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).save(address);
    }
}