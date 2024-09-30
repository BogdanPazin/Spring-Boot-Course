package com.luv2code.springcoredemo.common;

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
}
