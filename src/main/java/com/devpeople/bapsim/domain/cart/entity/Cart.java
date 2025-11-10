package com.devpeople.bapsim.domain.cart.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 장바구니 테이블
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
@Builder
public class Cart extends BaseEntity {

    /** 기본키 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** user FK  */
    @Column(nullable = false)
    private Long userId;

    /** cart FK  */
    @Column(nullable = false)
    private Long storeId;  // FK only


}
