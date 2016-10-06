package ru.knize.hyperloop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by knize on 24.09.16.
 */
@Controller
public class CMSIndexController {

    @GetMapping("/cms")
    public String cmsIndex(Model model) {
        return "/cms/cmsIndex";
    }
}
