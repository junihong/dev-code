package me.tamsil.hibernatevalidatorlist;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MaxSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSizeConstraint {
    String message() default "The input list cannot contain more 4 Users.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
