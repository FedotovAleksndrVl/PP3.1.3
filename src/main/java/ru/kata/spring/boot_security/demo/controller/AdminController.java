package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    final private UserServiceImpl userService;
    final private RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = {"/" , "/index"})
    public String index() {
        if (userService.ifLogin()) {
            return "redirect:/user";
        }
        return "index";
    }

    @PostMapping("/create")
    public String createOne() {
        if (userService.ifLogin()) {
            return "redirect:/user";
        }
        roleService.saveRole(new Role(1l ,"ROLE_ADMIN","админ"));
        roleService.saveRole(new Role(2l ,"ROLE_USER","юзер"));
        List<Role> role =  roleService.findAllRole();
        User user = new User("admin","admin", role);
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/admin")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/admin/edit")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        if(user.getId() != null) {
            model.addAttribute("user", userService.getUserById(user.getId()));
        }
        model.addAttribute("roles", roleService.findAllRole());
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAllRole());
            return "edit";
        } else {
            if (user.getId() == null) {
                userService.saveUser(user);
            } else {
                userService.updateUser(user);
            }
            return "redirect:/admin";
        }
    }

    @PostMapping(value = "/admin/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
            userService.removeUserById(user.getId());
        return "redirect:/admin";
    }
}