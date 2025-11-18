package com.devpeople.bapsim.domain.event.service;

import com.devpeople.bapsim.domain.event.entity.Event;
import com.devpeople.bapsim.domain.event.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    // EventService에 Mock 객체 주입
    @InjectMocks
    private EventService eventService;

    // EventRepository Mock 객체 생성
    @Mock
    private EventRepository eventRepository;

    private Event activeEvent;
    private Event closedEvent;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();

        activeEvent = Event.builder()
                .id(1L)
                .name("1주년 기념 이벤트")
                .description("신규/기존 회원 대상 쿠폰 증정 이벤트")
                .type("COUPON")
                .status("ACTIVE")
                .startAt(now.minusDays(1)) // 어제 시작
                .endAt(now.plusDays(7))   // 7일 뒤 종료
                .isEnded('N')
                .build();

        closedEvent = Event.builder()
                .id(2L)
                .name("종료된 이벤트")
                .description("이미 종료된 이벤트입니다.")
                .type("NOTICE")
                .status("CLOSED")
                .startAt(now.minusDays(30))
                .endAt(now.minusDays(1)) // 어제 종료
                .isEnded('Y')
                .build();
    }

    @Test
    @DisplayName("ID로 이벤트 조회 성공")
    void getEventById_Success() {
        // Given: eventRepository.findById(1L) 호출 시 activeEvent 반환 설정
        given(eventRepository.findById(1)).willReturn(Optional.of(activeEvent));

        // When: 서비스 메서드 호출
        Event foundEvent = eventService.getEventById(1);

        // Then: 반환된 이벤트의 ID와 이름 검증
        assertThat(foundEvent).isNotNull();
        assertThat(foundEvent.getId()).isEqualTo(1L);
        assertThat(foundEvent.getName()).isEqualTo("1주년 기념 이벤트");
        verify(eventRepository).findById(1);
    }

    @Test
    @DisplayName("존재하지 않는 ID로 이벤트 조회 시 예외 발생")
    void getEventById_NotFound() {
        // Given: eventRepository.findById(99) 호출 시 빈 Optional 반환 설정
        given(eventRepository.findById(99)).willReturn(Optional.empty());

        // When & Then: 메서드 호출 시 IllegalArgumentException이 발생하는지 검증
        assertThrows(IllegalArgumentException.class, () -> {
            eventService.getEventById(99);
        }, "이벤트를 찾을 수 없습니다. id=99"); // 예외 메시지 검증 추가
    }

    @Test
    @DisplayName("이벤트 목록 전체 조회 성공")
    void getEventList_Success() {
        // Given: eventRepository.findAll() 호출 시 두 이벤트 목록 반환 설정
        List<Event> expectedList = Arrays.asList(activeEvent, closedEvent);
        given(eventRepository.findAll()).willReturn(expectedList);

        // When: 서비스 메서드 호출
        List<Event> actualList = eventService.getEventList();

        // Then: 반환된 목록의 크기와 내용 검증
        assertThat(actualList).isNotNull();
        assertThat(actualList).hasSize(2);
        assertThat(actualList).containsExactly(activeEvent, closedEvent);
        verify(eventRepository).findAll();
    }

    @Test
    @DisplayName("새로운 이벤트 저장 성공")
    void saveEvent_Success() {
        // Given: eventRepository.save(any) 호출 시 입력된 Event 객체 그대로 반환 설정 (ID는 3L로 가정)
        Event newEvent = activeEvent.toBuilder().id(null).name("신규 생성 이벤트").build();
        Event savedEventWithId = newEvent.toBuilder().id(3L).build();

        given(eventRepository.save(any(Event.class))).willReturn(savedEventWithId);

        // When: 서비스 메서드 호출
        Event savedEvent = eventService.saveEvent(newEvent);

        // Then: 저장된 이벤트가 null이 아니고 ID가 할당되었는지 확인
        assertThat(savedEvent).isNotNull();
        assertThat(savedEvent.getId()).isEqualTo(3L);
        assertThat(savedEvent.getName()).isEqualTo("신규 생성 이벤트");
        verify(eventRepository).save(newEvent);
    }

    @Test
    @DisplayName("이벤트 논리적 삭제 성공")
    void deleteEvent_Success() {
        // 1. Given: 조회 요청 시 activeEvent가 존재한다고 가정
        given(eventRepository.findById(1)).willReturn(Optional.of(activeEvent));

        // 2. Given: save 요청 시 변경된 객체를 반환한다고 가정
        given(eventRepository.save(any(Event.class))).willAnswer(invocation -> invocation.getArgument(0));

        // When: 서비스 메서드 호출
        Event deletedEvent = eventService.deleteEvent(1);

        // Then
        assertThat(deletedEvent.getIsEnded()).isEqualTo('Y');
        assertThat(deletedEvent.getDescription()).isEqualTo("삭제");

        // 참고: status 필드도 "CLOSED"와 같은 상태로 변경하는 로직을 추가할 수 있습니다.
        // assertThat(deletedEvent.getStatus()).isEqualTo("CLOSED");

        verify(eventRepository).findById(1);
        verify(eventRepository).save(deletedEvent);
    }

    @Test
    @DisplayName("존재하지 않는 이벤트 삭제 시 예외 발생")
    void deleteEvent_NotFound() {
        // Given: eventRepository.findById(99) 호출 시 빈 Optional 반환 설정
        given(eventRepository.findById(99)).willReturn(Optional.empty());

        // When & Then: 메서드 호출 시 IllegalArgumentException이 발생하는지 검증
        assertThrows(IllegalArgumentException.class, () -> {
            eventService.deleteEvent(99);
        });
    }
}