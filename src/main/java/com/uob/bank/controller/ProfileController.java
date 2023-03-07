package com.uob.bank.controller;

import com.uob.bank.dto.UserRegistrationDto;
import com.uob.bank.model.User;
import com.uob.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    @GetMapping("/change-password")
    public String createPasswordForm() {
        return "change-password";
    }

    @PostMapping("/change-password")
    public String updatePasswordForm(HttpServletRequest request, @ModelAttribute("user") User user) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        return userService.updatePassword(user, oldPassword, newPassword);
    }

    @GetMapping("/change-profile")
    public String createProfileForm() {
        return "change-profile";
    }

    @PostMapping("/change-profile")
    public String updateProfileForm(@ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto, @ModelAttribute("user") User user) {
        return userService.updateProfile(userRegistrationDto, user);
    }

    @GetMapping("/delete-account")
    public String createDeleteForm() {
        return "delete-account";
    }

    @PostMapping("/delete-account")
    public String deleteDeleteForm(HttpServletRequest request, @ModelAttribute("user") User user) {
        String password = request.getParameter("checkPassword");
        return userService.deleteAccount(password, user);
    }


    @ModelAttribute("user")
    private User userAttribute(Principal principal) {
        if (Objects.nonNull(principal)) {
            return userService.getUserByEmail(principal.getName());
        } else
            return null;
    }

    @ModelAttribute("userRegistrationDto")
    private UserRegistrationDto userRegistrationDtoAttribute(@ModelAttribute("user") User user) {
        return new UserRegistrationDto(user.getFirstName(), user.getLastName(), user.getContactNumber(), user.getAddress(), user.getNomineeDetails());
    }
}
