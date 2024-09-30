package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect // JAVA KLASA KOJA IMA KOLEKCIJU ADVICE-OVA U SRODSTVU (before, after,...)
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // OVO SE IZVRSAVA BEZ OBZIRA NA REZULTAT POZVANE METODE(ISTO JE KAO FINALLY BLOK ZA TRY/CATCH)
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
            returning = "result") // DAJEM IME PARAMETRA GDE CE AOP DA UBACI REZULTAT
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result){
              // IME PARAMETRA METODE MORA DA BUDE ISTI KAO IME GORE NAVEDENOG PARAMETRA U KOME CE DA SE CUVA VREDNOST


        String method = joinPoint.getSignature().toShortString();
        System.out.println("====>>> Executing @AfterReturning on method: " + method);

        System.out.println("The result is: " + result);


        // MENJAM REZULTAT PRE NEGO STO DODJE DO MAIN APP
        convertNamesToUpperCase(result);

        System.out.println("The result after converting: " + result);
    }

    private void convertNamesToUpperCase(List<Account> result) {
        for(Account acc : result){
            String name = acc.getName().toUpperCase();

            acc.setName(name);
        }
    }




    // OVDE PRIMENJUJEM DEKLARACIJU I OVI ADVICE-OVI CE SE POZIVATI NA SVE METODE U DAO PAKETU
    // ALI JEDINO NECE NA GETERE I SETERE
    @Before("com.luv2code.aopdemo.aspect.PointcutDeclarations.excludeGetterAndSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("==========>>> Executing @Before advice on method");


        // SADA PRIKAZUJEM ZAPIS SAME METODE KOJE POZIVAM IZ MAIN DELA
        // ODNOSNO, ISPISUJEM TIP REZULTATA, KLASU IZ KOJE SE POZIVA METODA, IME METODE I TIPOVE PARAMETARA ZA TU METODU
        org.aspectj.lang.reflect.MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // DOBIJAM PARAMETRE METODA
        Object[] args = joinPoint.getArgs();

        for(Object obj : args){
            System.out.println(obj);
        }
    }

    // OVO SE ODNOSI NA SVE METODE IZ PAKETA dao, PRI CEMU SE PRVA * ODNOSI NA SVE KLASE A DRUGA * ZA SVE METODE
    // DOK ZAGRADA JE ISTA I OZNACAVA DA METODE IMAJU BILO KOJI BROJ PARAMETARA I TO BILO KOG TIPA
//    @Before("execution(public * com.luv2code.aopdemo.dao.*.*(..))")
//    public void beforeAddAccountAdvice(){
//        System.out.println("==========>>> Executing @Before advice on method");
//    }

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
