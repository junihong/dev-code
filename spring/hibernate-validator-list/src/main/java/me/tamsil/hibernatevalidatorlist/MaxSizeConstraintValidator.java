package me.tamsil.hibernatevalidatorlist;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class MaxSizeConstraintValidator implements ConstraintValidator<MaxSizeConstraint, List<User>> {

    @Override
    public boolean isValid(List<User> users, ConstraintValidatorContext constraintValidatorContext) {
        return users.size() <= 4;
    }
}
