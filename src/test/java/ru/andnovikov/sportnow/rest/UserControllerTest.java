package ru.andnovikov.sportnow.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.service.UserService;
import ru.andnovikov.sportnow.service.dto.UserDTO;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    private final String baseUrl = "/api/users";
    private final String userUrl = "/api/user";

    private static final long FIELD_ID = 0;
    private static final String FIELD_LOGIN = "login";
    private static final String FIELD_PASSWORD = "123456";
    private static final String FIELD_FIRSTNAME = "test";
    private static final String FIELD_LASTNAME = "test";
    private static final Date FIELD_BIRTHDAY = new Date();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUser() throws Exception {
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

        when(userService.createUser(any())).thenReturn(user);
        String json = new ObjectMapper().writeValueAsString(userDto);
        mvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateUser() throws Exception {
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

        when(userService.updateUser(any())).thenReturn(Optional.of(userDto));
        String json = new ObjectMapper().writeValueAsString(userDto);
        mvc.perform(put(baseUrl+"/" + "{userId}", FIELD_ID).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isAccepted());
    }

    @Test
    public void getCurrentUser() throws Exception{
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
        when(userService.getUserWithAuthorities()).thenReturn(Optional.of(user));
        String json = new ObjectMapper().writeValueAsString(userDto);
        mvc.perform(get(userUrl).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }
}