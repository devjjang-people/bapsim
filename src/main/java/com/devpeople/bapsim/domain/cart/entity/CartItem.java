package com.devpeople.bapsim.domain.cart.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cart_items")
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cartId;     // FK only
    private Long productId;  // FK only

    private Integer quantity;
    private Integer unitPrice;
    private String note;


}
