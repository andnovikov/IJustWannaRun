package ru.andnovikov.sportnow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.domain.enumeration.EventKind;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event save(Event events);

    Page<Event> findAll(Pageable pageable);

    List<Event> findOpenByKindAndStatus(EventKind eventKind);

    Optional<Event> findOne(Long id);

    void delete(Long id);
}
