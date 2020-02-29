package ru.andnovikov.sportnow.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.service.UserService;
import ru.andnovikov.sportnow.service.dto.UserDTO;

import java.util.List;

import static org.reflections.Reflections.log;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
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

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        log.debug("REST request to get user", userId);
        return new ResponseEntity<>(userService.getUserWithAuthorities(userId).orElseThrow(NoDataFoundException::new), HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        log.debug("REST request to save user", user);
        User result = userService.createUser(user);
        result.setPassword(null);
        user = new UserDTO(result);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/api/users/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO user) {
        log.debug("REST request to update user", user);
        UserDTO result = userService.updateUser(user).orElse(null);
        result.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/user")
    public ResponseEntity<UserDTO> getCurrentUser() {
        log.debug("REST request to get current user");
        return new ResponseEntity<>(new UserDTO(userService.getUserWithAuthorities().orElseThrow(NoDataFoundException::new)), HttpStatus.OK);
    }

}
