package com.devpeople.bapsim.domain.event.service;

import com.devpeople.bapsim.domain.address.entity.Address;
import com.devpeople.bapsim.domain.event.entity.Event;
import com.devpeople.bapsim.domain.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;

    public Event getEventById(Integer id) {

        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다. id=" + id));
    }

    public List<Event> getEventList() {

        return eventRepository.findAll();
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event deleteEvent(Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("이벤트를 찾을 수 없습니다. id=" + id));
        event.setDescription("삭제");
        event.setIsEnded('Y');

        return eventRepository.save(event);
    }
}
