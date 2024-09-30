package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @After("execution(public * com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyAdvice(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("====>>> Executing @AfterFinally on method: " + method);
    }


    @AfterThrowing(pointcut = "execution(public * com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
                throwing = "exc")
    public void afterThrowingExceptionAdvice(JoinPoint joinPoint, Throwable exc){

        String method = joinPoint.getSignature().toShortString();
        System.out.println("====>>> Executing @AfterThrowing on method: " + method);


        System.out.println("====>>> The exception is: " + exc);
    }


    @AfterReturning(
            pointcut = "execution(public * com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result") 
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result){

        String method = joinPoint.getSignature().toShortString();
        System.out.println("====>>> Executing @AfterReturning on method: " + method);

        System.out.println("The result is: " + result);

        convertNamesToUpperCase(result);

        System.out.println("The result after converting: " + result);
    }

    private void convertNamesToUpperCase(List<Account> result) {
        for(Account acc : result){
            String name = acc.getName().toUpperCase();

            acc.setName(name);
        }
    }


    @Before("com.luv2code.aopdemo.aspect.PointcutDeclarations.excludeGetterAndSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("==========>>> Executing @Before advice on method");

        org.aspectj.lang.reflect.MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for(Object obj : args){
            System.out.println(obj);
        }
    }

//    @Before("execution(public * com.luv2code.aopdemo.dao.*.*(..))")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }


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
