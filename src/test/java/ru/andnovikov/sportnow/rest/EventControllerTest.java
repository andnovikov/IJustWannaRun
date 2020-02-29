package ru.andnovikov.sportnow.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.domain.enumeration.EventKind;
import ru.andnovikov.sportnow.domain.enumeration.EventStatus;
import ru.andnovikov.sportnow.service.EventService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
class EventControllerTest {

    private final String baseUrl = "/api/events";

    private static final long FIELD_ID = 1;
    private static final String FIELD_NAME = "TestEvent";
    private static final Date FIELD_DATE = new Date();
    private static final Date FIELD_DATE_REGSTART = new Date();
    private static final Date FIELD_DATE_REGEND = new Date();
    private static final String FIELD_COUNTRY = "Россия";
    private static final String FIELD_CITY = "Москва";
    private static final String FIELD_SHORT_TITLE = "Hi, wrld!";
    private static final String FIELD_TITLE = "Hello, world!";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EventService eventService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createEvent() throws Exception {
        Event event = new Event(FIELD_NAME, EventKind.RUNNING, FIELD_DATE, FIELD_DATE_REGSTART, FIELD_DATE_REGEND,
                EventStatus.OPEN, "", FIELD_COUNTRY, FIELD_CITY, FIELD_SHORT_TITLE, FIELD_TITLE, null);

        when(eventService.save(any())).thenReturn(event);
        String json = new ObjectMapper().writeValueAsString(event);
        mvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void updateEvent() throws Exception {
        Event event = new Event(FIELD_NAME, EventKind.RUNNING, FIELD_DATE, FIELD_DATE_REGSTART, FIELD_DATE_REGEND,
                EventStatus.OPEN, "", FIELD_COUNTRY, FIELD_CITY, FIELD_SHORT_TITLE, FIELD_TITLE, null);

        when(eventService.save(any())).thenReturn(event);
        String json = new ObjectMapper().writeValueAsString(event);
        mvc.perform(put(baseUrl + "/" + "{eventId}", FIELD_ID).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isAccepted());
    }

    @Test
    void getAllEvent() throws Exception {
        Event event = new Event(FIELD_NAME, EventKind.RUNNING, FIELD_DATE, FIELD_DATE_REGSTART, FIELD_DATE_REGEND,
                EventStatus.OPEN, "", FIELD_COUNTRY, FIELD_CITY, FIELD_SHORT_TITLE, FIELD_TITLE, null);
        List<Event> list = new ArrayList<>();
        list.add(event);
        Page<Event> page = new PageImpl<Event>(list);

        when(eventService.findAll(any())).thenReturn(page);
        String json = new ObjectMapper().writeValueAsString(event);
        mvc.perform(get(baseUrl).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    void getEvent() throws Exception {
        Event event = new Event(FIELD_NAME, EventKind.RUNNING, FIELD_DATE, FIELD_DATE_REGSTART, FIELD_DATE_REGEND,
                EventStatus.OPEN, "", FIELD_COUNTRY, FIELD_CITY, FIELD_SHORT_TITLE, FIELD_TITLE, null);

        when(eventService.findOne(any())).thenReturn(Optional.of(event));
        String json = new ObjectMapper().writeValueAsString(event);
        mvc.perform(get(baseUrl + "/" + "{eventId}", FIELD_ID).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEventError() throws Exception {
        mvc.perform(delete(baseUrl + "/" +"{eventId}", FIELD_ID))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    void deleteEvent() throws Exception {
        mvc.perform(delete(baseUrl + "/" +"{eventId}", FIELD_ID))
                .andExpect(status().isNoContent());
    }


}