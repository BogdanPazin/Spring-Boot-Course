package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    // promenjen je default-ni bean id sa ovom zagradom kod anotacije
    @Bean("aquatic") //ova anotacija omogucava da se third-party klasa koristi u mojoj spring aplikaciji kao spring bean
    //on je dao primer za amazon

    public Coach swimCoach(){ //bean id je isti kao metod u ovom primeru
        return new SwimCoach();
    }
}
