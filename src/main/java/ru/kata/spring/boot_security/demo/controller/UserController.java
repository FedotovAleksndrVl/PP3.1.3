package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    @Autowired
    public UserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String listUser(Model model) {
        return "user";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/edit")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        System.out.println("Это из контроллера, из запроса id на добавление пользователя " + user.getId());
        if(user.getId() != null) {
            model.addAttribute("user", userService.getUserById(user.getId()));
        }
        return "edit";
    }

    @PostMapping("/users/edit")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        System.out.println("Это из контроллера, из запроса id на изменение пользователя " + user.getId());
        if (bindingResult.hasErrors()) {
            return "edit.html";
        } else {
            if (user.getId() == null) {
                userService.saveUser(user);
            } else {
                userService.updateUser(user);
            }
            return "redirect:/users";
        }
    }

    @PostMapping(value = "/users/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        System.out.println("Это из контроллера, из запроса id на удаление пользователя " + user.getId());
        //System.out.println(user);

            userService.removeUserById(user.getId());
        return "redirect:/users";
    }
}