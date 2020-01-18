package ru.andnovikov.sportnow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome(){
        return "events";
    }

    @GetMapping("/event-show")
    public String event(){
        return "event-edit";
    }

    @GetMapping("/sign_up")
    public String signUp() { return "sign_up";}

    @GetMapping("/sign_in")
    public String signIn() { return "sign_in";}

}
