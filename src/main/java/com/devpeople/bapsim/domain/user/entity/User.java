package com.devpeople.bapsim.domain.user.entity;

import com.devpeople.bapsim.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 회원 테이블
 * */

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    /** 기본키 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 로그인용 이메일 */
    @Column(nullable = false, unique = true)
    private String email;

    /** 비밀번호 해시값 */
    @Column(name = "password", nullable = false) // password_hash
    private String password;

    /** 사용자 이름 */
    @Column(nullable = false, length = 100)
    private String name;

    /** 연락처 */
    @Column(length = 20)
    private String phone;

    /** 권한 (USER / ADMIN) */
    @Column(nullable = false, length = 20)
    private String role;

    /** 계정 상태 (ACTIVE / WITHDRAWN) */
    @Column(nullable = false, length = 20)
    private String status;

    /** 최근 로그인 시각 */
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;
}
