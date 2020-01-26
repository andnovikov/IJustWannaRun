package ru.andnovikov.sportnow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.andnovikov.sportnow.security.SecurityUtils;

@Controller
public class PageController {

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

    @GetMapping("/profile")
    public String profile() {
        if (SecurityUtils.isAuthenticated()) {
            return "profile";
        }
        else {
            return "redirect:/";
        }
    }

}
