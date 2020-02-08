package ru.andnovikov.sportnow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.andnovikov.sportnow.security.SecurityUtils;

@Controller
public class PageController {

    @GetMapping("/")
    public String welcome(){
        return "events";
    }

    @GetMapping("/event/{eventId}")
    public String event(Model model, @PathVariable Long eventId){
        model.addAttribute("event_id", eventId);
        return "event";
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
