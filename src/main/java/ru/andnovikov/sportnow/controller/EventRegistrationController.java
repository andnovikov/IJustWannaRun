package ru.andnovikov.sportnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.andnovikov.sportnow.domain.Event;
import ru.andnovikov.sportnow.domain.User;
import ru.andnovikov.sportnow.domain.Registration;
import ru.andnovikov.sportnow.rest.NoDataFoundException;
import ru.andnovikov.sportnow.service.EventService;
import ru.andnovikov.sportnow.service.RegistrationService;
import ru.andnovikov.sportnow.service.UserService;

import java.util.Date;
import java.util.Optional;

@Controller
public class EventRegistrationController {

    private final EventService eventService;
    private final UserService userService;
    private final RegistrationService registrationService;

    @Autowired
    public EventRegistrationController (EventService eventService, UserService userService, RegistrationService registrationService) {
        this.eventService = eventService;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping(value = "/event/{eventId}/reg")
    public String createRegistration (@PathVariable String eventId, Model model) {
        Optional<Event> event = eventService.findOne(eventId);
        if (!event.isPresent()) {
            throw new NoDataFoundException();
        }
        User user = userService.getTestUser().get();
        Registration registration = new Registration();
        registration.setEvent(event.get());
        registration.setRegDate(new Date());
        registration.setRegNumber(0);
        model.addAttribute("user", user);
        model.addAttribute("registration", registration);

        return "event-reg";
    }

    //TODO make this through registration api
    @PostMapping(value = "/registration")
    public String addRegistration (@RequestParam String event_id) {
        Optional<Event> event = eventService.findOne(event_id);
        User user = userService.getTestUser().get();

        Registration registration = new Registration();
        registration.setEvent(event.get());
        registration.setRegDate(new Date());
        registration.setRegNumber(0);
        registrationService.save(registration);

        user.addRegistration(registration);
        userService.save(user);
        return "redirect:/";
    }

}
