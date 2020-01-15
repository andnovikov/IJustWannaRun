package ru.andnovikov.sportnow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.sportnow.domain.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

}
