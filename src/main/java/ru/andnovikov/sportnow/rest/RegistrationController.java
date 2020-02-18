package ru.andnovikov.sportnow.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.andnovikov.sportnow.domain.Registration;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.domain.enumeration.RegStatus;
import ru.andnovikov.sportnow.service.RegistrationService;
import ru.andnovikov.sportnow.service.UserService;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.reflections.Reflections.log;

@Slf4j
@RestController
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserService userService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @PostMapping("/api/registrations")
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) throws URISyntaxException {
        log.debug("REST request to save Registration : {}", registration);
        User user = userService.getUserWithAuthorities().orElseThrow(NoDataFoundException::new);
        return new ResponseEntity<>(registrationService.save(registration), HttpStatus.CREATED);
    }

    @GetMapping("/api/registrations/{id}")
    public ResponseEntity<Registration> getRegistration(@PathVariable Long id) {
        log.debug("REST request to get Registration : {}", id);
        Optional<Registration> registration = registrationService.findOne(id);
        return new ResponseEntity<>(registration.get(), HttpStatus.OK);
    }

    @GetMapping("/api/registrations")
    public ResponseEntity<List<Registration>> getRegistrations(@RequestParam RegStatus status) {
        // TODO Need another method to filter by User
        log.debug("REST request to get a page of Registration");
        User user = userService.getUserWithAuthorities().orElseThrow(NoDataFoundException::new);
        return new ResponseEntity<>(registrationService.getAllByUserAndStatus(user, status), HttpStatus.OK);
    }

    @PutMapping("/api/registrations/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long id, @RequestBody Registration registration) {
        log.debug("REST request to update Registration : {}", id);
        return new ResponseEntity<>(registrationService.save(registration), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/registrations/{id}")
    public ResponseEntity<Registration> deleteRegistration(@PathVariable Long id) {
        log.debug("REST request to delete Registration : {}", id);
        registrationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/api/registrations/{id}")
    public ResponseEntity<Registration> changeRegistrationStatus(@PathVariable Long id, @RequestParam RegStatus status) {
        log.debug("REST request to confirm Registration : {}", id);
        Registration registration = registrationService.findOne(id).orElseThrow(NoDataFoundException::new);

        Long registrationUserId = registration.getUser().getId();
        Long currentUserId = userService.getUserWithAuthorities().get().getId();
        log.debug("changeRegistrationStatus found Registration for userId : {}", registrationUserId);
        log.debug("changeRegistrationStatus current userId : {}", currentUserId);
        if (registrationUserId.equals(currentUserId)) {
            registration.setStatus(status);
            return new ResponseEntity<>(registrationService.save(registration), HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
