package ru.knize.hyperloop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.knize.hyperloop.entities.AccountEntity;
import ru.knize.hyperloop.services.AccountService;

/**
 * Created by knize on 15.10.16.
 */
@Controller
public class RegistrationController {

    @Autowired
    AccountService accountService;

    @GetMapping("/registration")
    public String init() {
        return "registration";
    }

    @PostMapping("/registration")
    public void registrate(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "email") String email,
                           Model model) {

        AccountEntity account = new AccountEntity();
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(password);
        account.setVerified((byte) 1);
        boolean success = accountService.add(account);
        model.addAttribute("success", success);


    }
}
