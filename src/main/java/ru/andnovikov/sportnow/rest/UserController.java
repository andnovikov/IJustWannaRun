package ru.andnovikov.sportnow.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.andnovikov.sportnow.domain.Registration;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.service.RegistrationService;
import ru.andnovikov.sportnow.service.UserService;

import java.net.URISyntaxException;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @Autowired
    public UserController (UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }
    
    @PostMapping("/user/{userId}/register")
    public ResponseEntity<Registration> createUserRegistration(@PathVariable String userId, @RequestParam String eventId) throws URISyntaxException {
        log.debug("REST request to save user registration : {}", userId);

        // TODO check for authority for user/self registration
        User user = userService.getTestUser().orElseThrow(NoDataFoundException::new);
        Registration registration = registrationService.save(registrationService.newRegistration(userId, eventId));
        user.addRegistration(registration);
        userService.save(user);
        return new ResponseEntity<>(registration, HttpStatus.OK);
    }

}
