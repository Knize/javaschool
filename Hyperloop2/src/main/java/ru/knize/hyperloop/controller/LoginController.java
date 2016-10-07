package ru.knize.hyperloop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by knize on 07.10.16.
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String init(Model model){
        return "/login";
    }

}
