package ru.kata.spring.boot_security.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChekUniqueValidator.class)
@Documented
public @interface ChekUnique {
    String value() default "false";
    String message() default "{chekUnique.message}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
