package ru.andnovikov.sportnow.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Event> createEvent(@RequestBody Event event) throws URISyntaxException {
        log.debug("REST request to save Event : {}", event);
        return new ResponseEntity(eventService.save(event), HttpStatus.CREATED);
    }

    @PutMapping("/api/events/{eventId}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) throws URISyntaxException {
        log.debug("REST request to update Event : {}", event);
        return new ResponseEntity(eventService.save(event), HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/events")
    public ResponseEntity<List<Event>> getAllEvent(Pageable pageable) {
        log.debug("REST request to get a page of Event");
        Page<Event> page = eventService.findAll(pageable);
        return new ResponseEntity(page.getContent(), HttpStatus.OK);
    }

    @GetMapping("/api/events/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable Long eventId) {
        log.debug("REST request to get Event : {}", eventId);
        Optional<Event> event = eventService.findOne(eventId);
        return new ResponseEntity(event.get(), HttpStatus.OK);
    }

    @DeleteMapping("/api/events/{eventId}")
    public ResponseEntity<Event> deleteEvent(@PathVariable Long eventId) {
        log.debug("REST request to delete Event : {}", eventId);
        eventService.delete(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
