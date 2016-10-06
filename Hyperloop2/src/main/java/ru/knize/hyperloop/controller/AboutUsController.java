package ru.knize.hyperloop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by knize on 23.09.16.
 */
@Controller
public class AboutUsController {

    @GetMapping("/aboutUs")
    public String aboutUs(Model model) {
        return "/info/aboutUs";
    }

}
