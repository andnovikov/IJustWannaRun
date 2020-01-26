package ru.andnovikov.sportnow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.andnovikov.sportnow.domain.Registration;

import java.util.Optional;

public interface RegistrationService {

    Registration newRegistration(Long userId, Long eventId);

    Registration save(Registration registration);

    Page<Registration> findAll(Pageable pageable);

    Optional<Registration> findOne(Long id);

    void delete(Long id);

}
