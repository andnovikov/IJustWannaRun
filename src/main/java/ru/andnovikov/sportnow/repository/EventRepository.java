package ru.andnovikov.sportnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.domain.enumeration.EventKind;
import ru.andnovikov.sportnow.domain.enumeration.EventStatus;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    public List<Event> findAllByKindAndStatus(EventKind eventKind, EventStatus status);

}
