package ru.andnovikov.sportnow.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.domain.enumeration.EventKind;
import ru.andnovikov.sportnow.domain.enumeration.EventStatus;
import ru.andnovikov.sportnow.repository.EventRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Event}.
 */
@Service
public class EventServiceImpl implements EventService {

    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Save a events.
     *
     * @param event the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Event save(Event event) {
        log.debug("Request to save Event : {}", event);

        // initialization
        if (event.getStatus() == null) {
            event.setStatus(EventStatus.OPEN);
        }
        if (event.getUrl() == null) {
            event.setUrl("");
        }

        return eventRepository.save(event);
    }

    /**
     * Get all the event.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<Event> findAll(Pageable pageable) {
        log.debug("Request to get all Event");
        return eventRepository.findAll(pageable);
    }


    /**
     * Get one event by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Event> findOne(Long id) {
        log.debug("Request to get Event : {}", id);
        return eventRepository.findById(id);
    }

    /**
     * Delete the event by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Event : {}", id);
        eventRepository.deleteById(id);
    }

    /**
     * Get all the event.
     *
     * @param eventKind the event kind.
     * @return the list of entities.
     */
    @Override
    public List<Event> findOpenByKindAndStatus(EventKind eventKind) {
        log.debug("Request to get findOpenByKindAndStatus");
        return eventRepository.findAllByKindAndStatus(eventKind, EventStatus.OPEN);
    }
}
