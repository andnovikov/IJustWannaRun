package ru.andnovikov.sportnow.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.service.EventService;
// import ru.andnovikov.ijustwannarun.web.rest.errors.BadRequestAlertException;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.reflections.Reflections.log;

@Slf4j
@RestController
public class EventController {

    private static final String ENTITY_NAME = "event";

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventsService) {
        this.eventService = eventsService;
    }

    @PostMapping("/api/events")
    public Event createEvent(@RequestBody Event event) throws URISyntaxException {
        log.debug("REST request to save Event : {}", event);
        Event result = eventService.save(event);
        return result;
    }

    @PutMapping("/events")
    public Event updateEvent(@RequestBody Event event) throws URISyntaxException {
        log.debug("REST request to update Event : {}", event);
        Event result = eventService.save(event);
        return result;
    }

    @GetMapping("/api/events")
    public List<Event> getAllEvent(Pageable pageable) {
        log.debug("REST request to get a page of Event");
        Page<Event> page = eventService.findAll(pageable);
        return page.getContent();
    }

    @GetMapping("/api/events/{id}")
    public Event getEvent(@PathVariable Long id) {
        log.debug("REST request to get Event : {}", id);
        Optional<Event> event = eventService.findOne(id);
        return event.get();
    }

    @DeleteMapping("/api/events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        log.debug("REST request to delete Event : {}", id);
        eventService.delete(id);
    }
}
