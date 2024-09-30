package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // stavlja za / kao default da vraca tekst Hello World
    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    // stavlja novi link za "workout"
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run a half 5km!";
    }

    //novi link za "fortune"
    @GetMapping("/fortune")
    public String getDailyFortune(){
        return "Today is your lucky day!";
    }

}
