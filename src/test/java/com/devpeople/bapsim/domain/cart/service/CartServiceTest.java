package com.devpeople.bapsim.domain.cart.service;

import com.devpeople.bapsim.domain.cart.entity.Cart;
import com.devpeople.bapsim.domain.cart.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Test
    void cart_save_success() {
        // given
        Cart cart = Cart.builder().id(1L).userId(10L).storeId(20L).build();
        Mockito.when(cartRepository.save(Mockito.any()))
                .thenReturn(cart);

        // when
        Cart saved = cartService.save(10L, 20L);

        // then
        assertThat(saved.getId()).isEqualTo(1L);
        assertThat(saved.getUserId()).isEqualTo(10L);
        assertThat(saved.getStoreId()).isEqualTo(20L);
    }

    @Test
    void cart_find_success() {
        Cart cart = Cart.builder().id(1L).userId(10L).storeId(20L).build();
        Mockito.when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        Cart found = cartService.find(1L);

        assertThat(found.getId()).isEqualTo(1L);
    }

    @Test
    void cart_delete_success() {
        cartService.delete(1L);
        Mockito.verify(cartRepository).deleteById(1L);
    }
}
