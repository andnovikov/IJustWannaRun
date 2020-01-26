package ru.andnovikov.sportnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.domain.Registration;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    public List<Registration> findAllByEvent(Event event);

}
