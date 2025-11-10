package com.devpeople.bapsim.domain.address.entity;

import com.devpeople.bapsim.domain.user.entity.User;
import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 주소 테이블
 * */

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends BaseEntity {
    /** 기본키 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 사용자 외래키 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** 주소 이름 */
    @Column(length = 50)
    private String label;

    /** 수령인 이름 */
    @Column(name = "recipient_name", length = 100)
    private String recipientName;

    /** 수령인 연락처 */
    @Column(length = 20)
    private String phone;

    /** 우편번호 */
    @Column(length = 10)
    private String zipcode;

    /** 기본 주소 */
    @Column(length = 255, nullable = false)
    private String address1;

    /** 상세 주소 */
    @Column(length = 255)
    private String address2;

    /**
     * 주 배송지 여부 (사용자당 1개만 TRUE 가능)
     */
    @Column(name = "is_main_address", nullable = false)
    @Builder.Default
    private Boolean isMainAddress = false;

    /** 주소 삭제 여부 */
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
