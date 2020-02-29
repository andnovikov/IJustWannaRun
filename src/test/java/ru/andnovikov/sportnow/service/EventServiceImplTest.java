package ru.andnovikov.sportnow.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.domain.enumeration.EventKind;
import ru.andnovikov.sportnow.domain.enumeration.EventStatus;
import ru.andnovikov.sportnow.repository.EventRepository;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EventServiceImplTest {

    private static final long FIELD_ID = 1;
    private static final String FIELD_NAME = "TestEvent";
    private static final Date FIELD_DATE = new Date();
    private static final Date FIELD_DATE_REGSTART = new Date();
    private static final Date FIELD_DATE_REGEND = new Date();
    private static final String FIELD_COUNTRY = "Россия";
    private static final String FIELD_CITY = "Москва";
    private static final String FIELD_SHORT_TITLE = "Hi, wrld!";
    private static final String FIELD_TITLE = "Hello, world!";

    @InjectMocks
    EventServiceImpl eventService;

    @Mock
    EventRepository eventRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldSaveEvent() {
        Event event = new Event(FIELD_NAME, EventKind.RUNNING, FIELD_DATE, FIELD_DATE_REGSTART, FIELD_DATE_REGEND,
                                EventStatus.OPEN, "", FIELD_COUNTRY, FIELD_CITY, FIELD_SHORT_TITLE, FIELD_TITLE, null);
        when(eventRepository.save(event)).thenReturn(event);
        assertEquals(eventService.save(event), event);
    }

    @Test
    void shouldFindOneEvent() {
        Event event = new Event(FIELD_NAME, EventKind.RUNNING, FIELD_DATE, FIELD_DATE_REGSTART, FIELD_DATE_REGEND,
                EventStatus.OPEN, "", FIELD_COUNTRY, FIELD_CITY, FIELD_SHORT_TITLE, FIELD_TITLE, null);
        when(eventRepository.findById(FIELD_ID)).thenReturn(Optional.of(event));
        assertNotNull(eventService.findOne(FIELD_ID));
    }

}