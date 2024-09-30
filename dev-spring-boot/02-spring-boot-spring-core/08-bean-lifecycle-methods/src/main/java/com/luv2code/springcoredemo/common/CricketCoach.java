package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// anotacija @Component nad klasom oznacava obicnu java klasu kao spring bean
// odnosno oznacava je kao klasu koju moze da koristi i upravlja spring
// a i takodje dopusta da se ova klasa koristi za dependancy injection

@Component
public class CricketCoach implements Coach{

    public CricketCoach() {
        System.out.println("Creating cricket coach");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
