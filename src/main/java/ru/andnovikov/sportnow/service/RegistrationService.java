package ru.andnovikov.sportnow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.andnovikov.sportnow.domain.Registration;

import java.util.Optional;

public interface RegistrationService {

    Registration save(Registration registration);

    Page<Registration> findAll(Pageable pageable);

    Optional<Registration> findOne(String id);

    void delete(String id);

}
