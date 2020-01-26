package ru.andnovikov.sportnow.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.repository.RegistrationRepository;
import ru.andnovikov.sportnow.repository.UserRepository;
import ru.andnovikov.sportnow.rest.NoDataFoundException;
import ru.andnovikov.sportnow.service.UserService;

import java.util.Objects;

@Component
public class CascadeDeleteMongoEventListener extends AbstractMongoEventListener<Object> {

    private final RegistrationRepository registrationRepository;
    private final UserService userService;

    @Autowired
    public CascadeDeleteMongoEventListener(RegistrationRepository registrationRepository, UserService userService) {
        this.registrationRepository = registrationRepository;
        this.userService = userService;
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) throws RuntimeException {
        /*
        if (Objects.equals(event.getCollectionName(), "registrations")) {
            String id = null;
            for (Object key : event.getSource().values()) {
                id = String.valueOf(key);
            }

            User user = userService.getUserWithAuthorities().orElseThrow(NoDataFoundException::new);
            user.getRegistrations().remove(user.findRegistration(id));
            userRepository.save(user);
        }
        */
    }
}
