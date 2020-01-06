package ru.andnovikov.sportnow.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.andnovikov.sportnow.domain.Registration;
import ru.andnovikov.sportnow.repository.RegistrationRepository;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final Logger log = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    /**
     * Save a userRegistration.
     *
     * @param registration the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Registration save(Registration registration) {
        log.debug("Request to save UserRegistration : {}", registration);
        return registrationRepository.save(registration);
    }

    /**
     * Get all the userRegistration.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<Registration> findAll(Pageable pageable) {
        log.debug("Request to get all UserRegistration");
        return registrationRepository.findAll(pageable);
    }

    /**
     * Get one userRegistration by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Registration> findOne(String id) {
        log.debug("Request to get UserRegistration : {}", id);
        return registrationRepository.findById(id);
    }

    /**
     * Delete the userRegistration by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete UserRegistration : {}", id);
        registrationRepository.deleteById(id);
    }
}
