package ru.andnovikov.sportnow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventRegistrationController {

    @GetMapping(value = "/event/{eventId}/reg")
    public String createRegistration (Model model) {

        return "event-reg";
    }


}
