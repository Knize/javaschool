package ru.knize.hyperloop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by knize on 05.10.16.
 */
@Controller
public class WatchTicketsController {

    @GetMapping("/cms/watchTickets")
    public String init(Model model){
        return "/cms/watchTickets";
    }

}
