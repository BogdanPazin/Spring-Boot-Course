package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;

    // anotacija @autowired ukazuje springu da moze ovo da se koristi za injection
    // ako imam samo jedan konstruktor onda je anotacija @autowired opcionalna

    @Autowired
    public DemoController(@Qualifier("tennisCoach") Coach myCoach) {
        //ako koristim primary anotaciju, ne moram da imam qualifier u listi parametara za konstruktor

        this.myCoach = myCoach;
        System.out.println("Creating demo controller");
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
