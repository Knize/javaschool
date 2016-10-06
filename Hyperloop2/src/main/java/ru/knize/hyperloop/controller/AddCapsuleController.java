package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.services.CapsuleService;

/**
 * Created by knize on 24.09.16.
 */

@Controller
public class AddCapsuleController {

    @Autowired
    public CapsuleService service;


    @GetMapping("/cms/addCapsule")
    public String initAddCapsule(Model model) {
        model.addAttribute("capsuleList", service.getCapsuleList());
        return "/cms/addCapsule";
    }

    @Transactional
    @PostMapping("/cms/addCapsule")
    public String addCapsule(@RequestParam("carSlots") int carSlots, @RequestParam("seatsNumber") int seatsNumber, Model model){
        service.addCapsule(carSlots, seatsNumber);
        initAddCapsule(model);
        return "/cms/addCapsule";
    }
}
