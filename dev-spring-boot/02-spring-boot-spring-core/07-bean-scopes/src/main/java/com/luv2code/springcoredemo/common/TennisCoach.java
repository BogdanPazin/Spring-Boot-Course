package com.luv2code.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //sa ovim tipom bean-a ce se pri svakom inject-u praviti novi bean
                                                //samim tim razlicite injekcije nece pokazivati na isti bean
public class TennisCoach implements Coach{

    public TennisCoach() {
        System.out.println("Creating tennis coach");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your forehand";
    }
}
