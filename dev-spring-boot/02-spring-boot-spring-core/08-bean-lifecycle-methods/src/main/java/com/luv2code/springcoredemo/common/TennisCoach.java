package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{

    public TennisCoach() {
        System.out.println("Creating tennis coach");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your forehand";
    }

    @PostConstruct
    public void afterCreation(){
        System.out.println("In afterCreation() for " + getClass().getSimpleName());
    }

    @PreDestroy
    public void beforeDestroying(){
        System.out.println("In beforeDestroying() for " + getClass().getSimpleName());
    }
}
