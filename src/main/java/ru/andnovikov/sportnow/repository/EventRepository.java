package ru.andnovikov.sportnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.sportnow.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
