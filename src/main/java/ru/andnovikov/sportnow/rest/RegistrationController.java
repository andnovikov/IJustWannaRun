package ru.andnovikov.sportnow.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserService userService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @PostMapping("/registrations")
    public Registration createRegistration(@RequestBody Registration registration) throws URISyntaxException {
        log.debug("REST request to save Registration : {}", registration);
        Registration result = registrationService.save(registration);
        return result;
    }

    @GetMapping("/registrations/{id}")
    public Registration getRegistration(@PathVariable String id) {
        log.debug("REST request to get Registration : {}", id);
        Optional<Registration> registration = registrationService.findOne(id);
        return registration.get();
    }

    @GetMapping("/registrations")
    public List<Registration> getRegistrations(@RequestParam RegStatus status) {
        log.debug("REST request to get a page of Registration");
        User user = userService.getUserWithAuthorities().orElseThrow(NoDataFoundException::new);
        List<Registration> result = user.getRegistrations().stream()
                .filter(registration -> registration.getStatus() == status)
                .collect(Collectors.toList());
        return result;
    }

    @DeleteMapping("/registrations/{id}")
    public void deleteRegistrations(@PathVariable String id) {
        log.debug("REST request to delete Event : {}", id);
        registrationService.delete(id);
    }

}
