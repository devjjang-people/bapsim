package com.devpeople.bapsim.domain.order.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 *  주문(Order) 엔티티
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order")
public class Order extends BaseEntity {

    /**
     * 주문 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 주문한 사용자 ID
     */
    private Long userId;

    /**
     * 주문이 발생한 가게 ID
     */
    private Long storeId;

    /**
     * 주문 시점의 주소 스냅샷
     * - JSONB 형태로 저장하여, 주문 시점의 배송지 정보를 그대로 보존함
     * - 예: {"address":"서울시 강남구...", "receiver":"홍길동", "phone":"010-..."}
     */
    @Column(columnDefinition = "jsonb")
    private String addressSnapshot;

    /**
     * 주문 상태 (추후 Enum으로 변경예정?)
     */
    private String orderStatus;

    /**
     * 총 주문 금액
     */
    private Integer orderTotal;

    /**
     * 할인 금액
     */
    private Integer discountTotal;

    /**
     * 배달료
     */
    private Integer deliveryFee;

    /**
     * 최종 결제 금액
     */
    private Integer payableTotal;

    /**
     * 쿠폰 스냅샷
     * - 주문 시 사용된 쿠폰의 정보(할인율, 이름 등)를 JSON 형태로 보관
     * - 주문 이후 쿠폰 정책이 바뀌더라도 과거 주문 내역이 유지되도록 함
     */
    @Column(columnDefinition = "jsonb")
    private String couponSnapshot;

    /**
     * 사용자가 주문 목록에서 숨김 처리했는지 여부
     * - 삭제 대신 "사용자 숨김" 플래그로 관리 (soft delete 용도)
     */
    private Boolean hiddenForUser = false;
}
