package ru.andnovikov.sportnow.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.rest.vm.LoginVM;
import ru.andnovikov.sportnow.security.jwt.JwtTokenFilter;
import ru.andnovikov.sportnow.security.jwt.JwtTokenProvider;
import ru.andnovikov.sportnow.service.UserService;
import ru.andnovikov.sportnow.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

import static org.reflections.Reflections.log;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    public UserController (UserService userService, JwtTokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    /**
     * {@code GET /users} : get all users.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/api/users")
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

    @PostMapping("/api/user/authenticate")
    public ResponseEntity<UserDTO> authenticateUser(@RequestBody LoginVM loginVM) {
        Optional<User> user = userService.getUserWithAuthoritiesByLogin(loginVM.getUsername());
        if (user.isPresent()) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(loginVM.getUsername(), user.get().getAuthorities());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtTokenFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            UserDTO result = new UserDTO(user.get());
            result.setAccessToken(jwt);
            return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<UserDTO>((UserDTO) null, HttpStatus.UNAUTHORIZED);
        }
    }

}
