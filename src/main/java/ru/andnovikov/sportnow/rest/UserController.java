package ru.andnovikov.sportnow.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.andnovikov.sportnow.config.Constants;
import ru.andnovikov.sportnow.domain.Registration;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.service.RegistrationService;
import ru.andnovikov.sportnow.service.UserService;
import ru.andnovikov.sportnow.service.dto.UserDTO;

import java.net.URISyntaxException;
import java.util.List;

import static org.reflections.Reflections.log;

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

    /**
     * {@code GET /users} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(Pageable pageable) {
        final List<UserDTO> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        log.debug("REST request to get user", userId);
        // TODO check if not exists
        User user = userService.getUserWithAuthorities(userId).get();
        // TODO response user without password? maybe UserDTO
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        log.debug("REST request to save user", user);
        User result = userService.createUser(user);
        // TODO response user without password
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/register")
    public ResponseEntity<List<Registration>> getUserRegistrations(@PathVariable String userId) throws URISyntaxException {
        log.debug("REST request to get user registrations : {}", userId);

        // TODO check for authority for user/self registration
        User user = userService.getTestUser().orElseThrow(NoDataFoundException::new);
        return new ResponseEntity<>(user.getRegistrations(), HttpStatus.OK);
    }
    
    @PostMapping("/users/{userId}/register")
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
