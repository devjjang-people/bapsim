package com.devpeople.bapsim.domain.coupon.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "coupon")
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Long storeId;

    private String name;
    private String type;
    private Integer amount;
    private Integer percent;
    private Integer maxDiscount;
    private Integer minOrderAmt;

    private String status;

    private Integer issuedQty;
    private Integer perUserLimit;

    private String startAt;
    private String endAt;
}
