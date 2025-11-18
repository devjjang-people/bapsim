package com.devpeople.bapsim.domain.event.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 이벤트 테이블
 * */

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Event extends BaseEntity {
    /** 기본키 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 이벤트 명 */
    @Column(length = 100, nullable = false)
    private String name;

    /** 이벤트 설명 (관리자용) */
    @Column(columnDefinition = "TEXT")
    private String description;

    /** 이벤트 유형 */
    @Column(length = 30, nullable = false)
    private String type;

    /**
     * 이벤트 상태
     * - ACTIVE: 진행 중
     * - INACTIVE: 비활성화
     * - CLOSED: 종료됨
     */
    @Column(length = 20, nullable = false)
    private String status;

    /** 시작 시각 */
    @Column(name = "start_at")
    private LocalDateTime startAt;

    /** 종료 시각 */
    @Column(name = "end_at")
    private LocalDateTime endAt;

    /** 이벤트 종료 여부 (Y / N) */
    @Column(name = "is_ended")
    private char isEnded;
}
