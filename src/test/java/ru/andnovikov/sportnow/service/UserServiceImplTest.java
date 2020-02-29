package ru.andnovikov.sportnow.service;

import org.hibernate.mapping.Any;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.andnovikov.sportnow.config.Constants;
import ru.andnovikov.sportnow.domain.Authority;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.repository.AuthorityRepository;
import ru.andnovikov.sportnow.repository.UserRepository;
import ru.andnovikov.sportnow.service.dto.UserDTO;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    private static final long FIELD_ID = 0;
    private static final String FIELD_LOGIN = "login";
    private static final String FIELD_PASSWORD = "123456";
    private static final String FIELD_FIRSTNAME = "test";
    private static final String FIELD_LASTNAME = "test";
    private static final Date FIELD_BIRTHDAY = new Date();

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthorityRepository authorityRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldCreateUser() {
        UserDTO userDto = new UserDTO(FIELD_LOGIN,
                                      FIELD_PASSWORD,
                                      FIELD_FIRSTNAME,
                                      FIELD_LASTNAME,
                                      FIELD_BIRTHDAY);

        User user = new User(FIELD_LOGIN,
                             FIELD_PASSWORD,
                             FIELD_FIRSTNAME,
                             FIELD_LASTNAME,
                             FIELD_BIRTHDAY);

        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.createUser(userDto).getLogin()).isEqualTo(FIELD_LOGIN);
    }


    @Test
    void shouldCheckDuplicateUser() {
        UserDTO userDto = new UserDTO(FIELD_LOGIN,
                FIELD_PASSWORD,
                FIELD_FIRSTNAME,
                FIELD_LASTNAME,
                FIELD_BIRTHDAY);

        User user = new User(FIELD_LOGIN,
                FIELD_PASSWORD,
                FIELD_FIRSTNAME,
                FIELD_LASTNAME,
                FIELD_BIRTHDAY);

        when(userRepository.findOneByLogin(FIELD_LOGIN)).thenReturn(Optional.of(user));
        assertThrows(UsernameAlreadyUsedException.class, () -> {
            userService.createUser(userDto);
        });
    }

}