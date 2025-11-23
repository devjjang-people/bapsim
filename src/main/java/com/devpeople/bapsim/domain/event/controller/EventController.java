package com.devpeople.bapsim.domain.event.controller;

import com.devpeople.bapsim.domain.event.entity.Event;
import com.devpeople.bapsim.domain.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    // TODO 조회조건 추가 (ex. 사용자 식별자)
    @PostMapping("/eventList")
    public List<Event> getEventList(){
        return eventService.getEventList();
    }
}
