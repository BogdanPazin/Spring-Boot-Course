package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect 
@Component
public class MyDemoLoggingAspect {

    @Before("execution(public * com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice(){
        System.out.println("==========>>> Executing @Before advice on method");
    }

//    @Before("execution(public * add*(..))")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

//    @Before("execution(public * add*(com.luv2code.aopdemo.Account, ..))")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

    
//    @Before("execution(public * add*(com.luv2code.aopdemo.Account))")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

   
//    @Before("execution(public * add*())")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

   
//    @Before("execution(public void add*())")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

    
//    @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

    
//    @Before("execution(public void addAccount())")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }
}
