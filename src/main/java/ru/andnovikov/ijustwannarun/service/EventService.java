package ru.andnovikov.ijustwannarun.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.andnovikov.ijustwannarun.domain.Event;

import java.util.Optional;

/**
 * Service Interface for managing {@link Event}.
 */
public interface EventService {

    /**
     * Save a events.
     *
     * @param events the entity to save.
     * @return the persisted entity.
     */
    Event save(Event events);

    /**
     * Get all the events.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Event> findAll(Pageable pageable);


    /**
     * Get the "id" events.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Event> findOne(String id);

    /**
     * Delete the "id" events.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
