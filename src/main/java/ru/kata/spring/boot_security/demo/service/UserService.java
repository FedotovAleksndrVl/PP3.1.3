package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User getUserById(Long id);

    void removeUserById(long id);

    List<User> getAllUsers();

    void updateUser(User user);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}