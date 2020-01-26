package ru.andnovikov.sportnow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.andnovikov.sportnow.domain.Registration;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.domain.enumeration.RegStatus;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {

    Registration newRegistration(Long userId, Long eventId);

    Registration save(Registration registration);

    Page<Registration> findAll(Pageable pageable);

    Optional<Registration> findOne(Long id);

    List<Registration> getAllByUserAndStatus (User user, RegStatus status);

    void delete(Long id);

}
