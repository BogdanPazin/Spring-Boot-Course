package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect // JAVA KLASA KOJA IMA KOLEKCIJU ADVICE-OVA U SRODSTVU (before, after,...)
@Component
public class MyDemoLoggingAspect {

    // OVO SE ODNOSI NA SVE METODE IZ PAKETA dao, PRI CEMU SE PRVA * ODNOSI NA SVE KLASE A DRUGA * ZA SVE METODE
    // DOK ZAGRADA JE ISTA I OZNACAVA DA METODE IMAJU BILO KOJI BROJ PARAMETARA I TO BILO KOG TIPA
    @Before("execution(public * com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice(){
        System.out.println("==========>>> Executing @Before advice on method");
    }

    // ODNOSI SE NA METODE KOJE IMAJU BILO KOJI BROJ PARAMETARA(0 ILI VISE) I TO BILO KOG TIPA
//    @Before("execution(public * add*(..))")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

    // OVO SE ODNOSI NA METODE KOJE IMAJU PARAMETAR TIPA Account, ALI I ONE KOJE NAKON TOGA IMAJU BILO KOJI BROJ PARAMETARA (0 ILI VISE)
//    @Before("execution(public * add*(com.luv2code.aopdemo.Account, ..))")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

    // OVO SE ODNOSI NA METODE KOJE PRIMAJU JEDAN PARAMETAR TIPA Account
//    @Before("execution(public * add*(com.luv2code.aopdemo.Account))")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

    // OVO SE ODNOSI NA METODE KOJE POCINJU SA add I KOJE VRACAJU BILO KOJI TIP REZULTATA
//    @Before("execution(public * add*())")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

    // OVO SE ODNOSI NA SVE METODE KOJE POCINJU SA add
    // I NA METODE sa add U IMENU ALI KOJE VRACAJU TIP VOID
//    @Before("execution(public void add*())")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

    // OVO CE DA SE ODNOSI SAMO NA METODU addAccount iz INTERFACE AccountDAO
//    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

    // OVAJ POINTCUT EXPRESSION CE DA OBUHVATA SVE METODE addAccount() KOJE MI SE NALAZE U PROJEKTU
//    @Before("execution(public void addAccount())")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }
}
