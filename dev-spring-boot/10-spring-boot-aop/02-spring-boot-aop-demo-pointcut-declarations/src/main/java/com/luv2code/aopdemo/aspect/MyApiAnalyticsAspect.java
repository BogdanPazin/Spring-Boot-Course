package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

    // OVDE PRIMENJUJEM DEKLARACIJU I OVI ADVICE-OVI CE SE POZIVATI NA SVE METODE U DAO PAKETU
    // ALI JEDINO NECE NA GETERE I SETERE
    @Before("com.luv2code.aopdemo.aspect.PointcutDeclarations.excludeGetterAndSetter()")
    public void performApiAnalytics(){
        System.out.println("===> Performing API analytics...");
    }
}
