package ru.andnovikov.ijustwannarun.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.andnovikov.ijustwannarun.domain.Event;

import java.util.Optional;

public interface EventService {

    Event save(Event events);

    Page<Event> findAll(Pageable pageable);

    Optional<Event> findOne(String id);

    void delete(String id);
}
