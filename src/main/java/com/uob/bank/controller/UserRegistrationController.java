package com.uob.bank.controller;

import com.uob.bank.dto.UserRegistrationDto;
import com.uob.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("userRegistrationDto") UserRegistrationDto registrationDto) {
        return userService.save(registrationDto);
    }

    @ModelAttribute("userRegistrationDto")
    private UserRegistrationDto userRegistrationDtoAttribute() {
        return new UserRegistrationDto();
    }
}
