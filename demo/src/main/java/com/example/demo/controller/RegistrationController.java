package com.example.demo.controller;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    private UserService userService;

    private RoleService roleService;

    public RegistrationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    /*@GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "reg";
        }
        if (userService.findUser(user)) {
            user_id = userService.loadUserByUsername(user.getUsername()).getId();

            return "redirect:/home";
        } else return "login";
    }*/

    /*
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }*/
    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "reg";
        }
        if (userService.save(user) == 0) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "reg";
        }

        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String getAllUsers(Model model) {
        // This returns a JSON or XML with the users
        model.addAttribute("user", new User());
        return "reg";
    }


}
