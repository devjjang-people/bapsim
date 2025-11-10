package com.devpeople.bapsim.domain.cart.service;

import com.devpeople.bapsim.domain.cart.entity.CartItem;
import com.devpeople.bapsim.domain.cart.repository.CartItemRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartItemService cartItemService;

    @Test
    void cartItem_save_success() {
        // given
        CartItem cartItem = CartItem.builder()
                .id(1L)
                .cartId(100L)
                .productId(200L)
                .quantity(3)
                .unitPrice(12000)
                .note("곱빼기")
                .build();

        Mockito.when(cartItemRepository.save(Mockito.any())).thenReturn(cartItem);

        // when
        CartItem saved = cartItemService.save(100L, 200L, 3, 12000, "곱빼기");

        // then
        assertThat(saved.getId()).isEqualTo(1L);
        assertThat(saved.getQuantity()).isEqualTo(3);
        assertThat(saved.getUnitPrice()).isEqualTo(12000);
    }

    @Test
    void cartItem_get_success() {
        CartItem cartItem = CartItem.builder()
                .id(1L)
                .cartId(100L)
                .productId(200L)
                .quantity(2)
                .unitPrice(10000)
                .build();

        Mockito.when(cartItemRepository.findById(1L)).thenReturn(Optional.of(cartItem));

        CartItem found = cartItemService.get(1L);

        assertThat(found.getProductId()).isEqualTo(200L);
    }

    @Test
    void cartItem_delete_success() {
        cartItemService.delete(1L);
        Mockito.verify(cartItemRepository).deleteById(1L);
    }
}