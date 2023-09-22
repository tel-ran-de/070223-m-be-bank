package de.telran.telranbank.customer;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailDomainValidator.class)
// использовать только на свойствах объектов
@Target({ElementType.FIELD})
// отрабатывает во время запуска приложения
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedDomains {
    String message() default "Invalid email domain";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
