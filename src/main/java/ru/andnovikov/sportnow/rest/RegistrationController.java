package ru.andnovikov.sportnow.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.andnovikov.sportnow.domain.Registration;
import ru.andnovikov.sportnow.service.RegistrationService;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class RegistrationController {

    private static final String ENTITY_NAME = "user_registration";

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public Registration createRegistration(@RequestBody Registration registration) throws URISyntaxException {
        log.debug("REST request to save Registration : {}", registration);
        Registration result = registrationService.save(registration);
        return result;
    }

    @GetMapping("/registration/{id}")
    public Registration getEvent(@PathVariable String id) {
        log.debug("REST request to get Registration : {}", id);
        Optional<Registration> registration = registrationService.findOne(id);
        return registration.get();
    }

    @GetMapping("/registration")
    public List<Registration> getAllEvent(Pageable pageable) {
        log.debug("REST request to get a page of Registration");
        Page<Registration> page = registrationService.findAll(pageable);
        return page.getContent();
    }

    @DeleteMapping("/registration/{id}")
    public void deleteEvent(@PathVariable String id) {
        log.debug("REST request to delete Event : {}", id);
        registrationService.delete(id);
    }

}
