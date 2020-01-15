package ru.andnovikov.sportnow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.domain.Registration;

import java.util.List;

@Repository
public interface RegistrationRepository extends MongoRepository<Registration, String> {

    public List<Registration> findAllByEvent(Event event);

}
