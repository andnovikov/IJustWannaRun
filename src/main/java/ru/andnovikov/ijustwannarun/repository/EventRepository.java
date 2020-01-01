package ru.andnovikov.ijustwannarun.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.ijustwannarun.domain.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

}
