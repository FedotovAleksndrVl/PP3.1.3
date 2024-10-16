package ru.kata.spring.boot_security.demo.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChekUniqueValidator implements ConstraintValidator<ChekUnique, String> {

    public String value;
    public UserServiceImpl userService;

    @Autowired
    public ChekUniqueValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ChekUnique chekUnique) {
        value = chekUnique.value();
    }

    @Override
    public boolean isValid(String enterLogin, ConstraintValidatorContext constraintValidatorContext) {
//        System.out.println(value + " - " + enterLogin + " - " + Boolean.toString(userService.ifLogin(enterLogin)));
//        return Boolean.toString(!(userService.ifLogin(enterLogin))).equals(value);
        return true;
    }


}
