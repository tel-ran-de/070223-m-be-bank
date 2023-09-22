package de.telran.telranbank.customer;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class EmailDomainValidator implements ConstraintValidator<AllowedDomains, String> {
    private static final Set<String> allowedDomains = Set.of("gmail.com", "yahoo.com");

    @Override
    public void initialize(AllowedDomains constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return allowedDomains.contains(email.split("@")[1]);
    }
}
