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

@Slf4j
@RestController
@RequestMapping("/api")
public class EventController {

    private static final String ENTITY_NAME = "event";

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventsService) {
        this.eventService = eventsService;
    }

    @PostMapping("/event")
    public Event createEvent(@RequestBody Event event) throws URISyntaxException {
        log.debug("REST request to save Event : {}", event);
        Event result = eventService.save(event);
        return result;
    }

    @PutMapping("/event")
    public Event updateEvent(@RequestBody Event event) throws URISyntaxException {
        log.debug("REST request to update Event : {}", event);
        Event result = eventService.save(event);
        return result;
    }

    @GetMapping("/event")
    public List<Event> getAllEvent(Pageable pageable) {
        log.debug("REST request to get a page of Event");
        Page<Event> page = eventService.findAll(pageable);
        return page.getContent();
    }

    @GetMapping("/event/{id}")
    public Event getEvent(@PathVariable String id) {
        log.debug("REST request to get Event : {}", id);
        Optional<Event> event = eventService.findOne(id);
        return event.get();
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable String id) {
        log.debug("REST request to delete Event : {}", id);
        eventService.delete(id);
    }
}
