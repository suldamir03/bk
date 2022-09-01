package com.example.demo.controller;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.*;
import com.example.demo.service.BurgerService;
import com.example.demo.service.IngredientsService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    private IngredientsService ingredientsService;
    private BurgerService burgerService;
    private UserService userService;
    private OrderService orderService;
    @Autowired
    public MainController(IngredientsService ingredientsService, BurgerService burgerService, UserService userService, OrderService orderService) {
        this.ingredientsService = ingredientsService;
        this.burgerService = burgerService;
        this.userService = userService;
        this.orderService = orderService;
    }




    //
    @GetMapping
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal User user){

        model.addAttribute("burgers",burgerService.findAll());
        model.addAttribute("orders", orderService.findAll() );
/*
        orderService.findByUserId(userService.findUserById(user_id))
*/
        model.addAttribute("order", new Order());
        model.addAttribute("user", user);
        return "home";
    }
    //New burger
    @GetMapping("/design")
    public String designNewBurger(Model model, @AuthenticationPrincipal User user) {
        if (user == null) {
            return "redirect:/login";
        }

        Type[] types = Type.values();
        //TODO : do it
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    ingredientsService.filterByType(type));
        }

        model.addAttribute("design", new Burger());

        return "design";


    }
    @PostMapping("/design")
    public String postDesign(@ModelAttribute("burger")Burger burger) {
        burgerService.saveBurger(burger);
        return "redirect:/home";
    }


    @PostMapping("/addToOrder/{id}")
    public String addBurgerToOrder(@PathVariable("id") Long id, @ModelAttribute("order") Order order, @AuthenticationPrincipal User user) {
        if (user == null){
            return "redirect:/login";
        }
        order.setOrder_burgers(Collections.singletonList(burgerService.findBurger(id)));
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        orderService.addBurgerToOrder(order, burgerService.findBurger(id));
        return "redirect:/home";
    }




}
