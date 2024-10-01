package com.luv2code.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) 
public class TennisCoach implements Coach{

    public TennisCoach() {
        System.out.println("Creating tennis coach");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your forehand";
    }
}
