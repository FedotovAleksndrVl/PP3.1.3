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
import java.sql.SQLException;
import java.util.List;

@Controller
public class AdminController {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/create")
    public String createOne() {
        roleService.saveRole(new Role(Long.valueOf(1) ,"ROLE_ADMIN","админ"));
        roleService.saveRole(new Role(Long.valueOf(2) ,"ROLE_USER","юзер"));
        List<Role> role =  roleService.findAllRole();
        User user = new User("admin","admin", role);
        try {
            userService.saveUser(user);
        } catch (RuntimeException e) {
            return "index";
        }
        return "index";
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
            return "edit.html";
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