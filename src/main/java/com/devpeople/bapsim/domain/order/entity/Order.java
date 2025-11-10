package com.devpeople.bapsim.domain.order.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long storeId;

    @Column(columnDefinition = "jsonb")
    private String addressSnapshot;

    private String orderStatus;
    private Integer orderTotal;
    private Integer discountTotal;
    private Integer deliveryFee;
    private Integer payableTotal;

    @Column(columnDefinition = "jsonb")
    private String couponSnapshot;

    private Boolean hiddenForUser = false;
}
