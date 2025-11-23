package com.devpeople.bapsim.domain.user.repository;

import com.devpeople.bapsim.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

