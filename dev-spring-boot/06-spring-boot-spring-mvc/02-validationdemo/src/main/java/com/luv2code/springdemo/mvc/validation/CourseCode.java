package com.luv2code.springdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) //koliko dugo ce se cuvati anotacija ili koristiti
public @interface CourseCode {
    //definisem parametar anotacije, gde ako korisnik ne posalje nista onda ce po default-u da prosledi LUV
    public String value() default "LUV";

    //takodje parametar anotacije, za poruku mogu da posalju svoju, ali ako ne posalju onda se salje ova
    public String message() default "must start with LUV";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
