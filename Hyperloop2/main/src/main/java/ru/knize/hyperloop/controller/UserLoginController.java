package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.services.AccountService;

/**
 * Created by knize on 15.10.16.
 */
@Controller
public class UserLoginController {

    @Autowired
    AccountService accountService;

    @GetMapping("/userLogin")
    public String init() {
        return "userLogin";
    }

    @PostMapping("/userlogin")
    public void login(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         Model model) {
        boolean success = accountService.check(username, password);
        model.addAttribute("success", success);
    }
}
