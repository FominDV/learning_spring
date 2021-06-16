package ru.fomin.free_progect.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

    @GetMapping
    public String getModeratorPage(){
        return "moderator";
    }

}
