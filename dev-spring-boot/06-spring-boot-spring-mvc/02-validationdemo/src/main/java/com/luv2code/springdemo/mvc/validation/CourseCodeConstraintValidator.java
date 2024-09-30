package com.luv2code.springdemo.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
//OVA KLASA SADRZI LOGIKU GDE ODREDJUJEM DA LI SU PROSLEDJENI PODACI ISPRAVNI ILI NE

    private String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
        //sa ovim ce da se dodeli prosledjen prefiks iz anotacije
        coursePrefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
        //theCode je tekst koji je poslao korisnik u html formi

        boolean result;
        if(theCode != null){
            result = theCode.startsWith(coursePrefix);
        }
        else{
            //salje se true jer nisam nista poslao i nema nista za validaciju
            result = true;
        }
        //proveravam da li pocinje sa LUV
        return result;
    }
}
