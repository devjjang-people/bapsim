package com.devpeople.bapsim.domain.event.repository;

import com.devpeople.bapsim.domain.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}

