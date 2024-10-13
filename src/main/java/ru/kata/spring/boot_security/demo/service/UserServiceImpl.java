package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {



    @Override
    @Transactional
    public void saveUser(User user) {

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    @Transactional
    public void updateUser(User user) {

    }
}

