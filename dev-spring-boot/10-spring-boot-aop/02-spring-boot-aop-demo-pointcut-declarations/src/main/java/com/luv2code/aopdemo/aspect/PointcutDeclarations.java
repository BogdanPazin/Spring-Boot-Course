package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // AKO IMAM SAMO POINTCUT EXPRESSIONS ONDA JE OVA ANOTACIJA OPCIONALNA
        // MORA DA SE PISE AKO IMAM I ADVICE-OVE

public class PointcutDeclarations {
    @Pointcut("execution(public * com.luv2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){

    }

    @Pointcut("execution(public * com.luv2code.aopdemo.dao.*.get*(..))")
    public void forGetters(){

    }

    @Pointcut("execution(public * com.luv2code.aopdemo.dao.*.set*(..))")
    public void forSetters(){

    }

    // KOMBINACIJA POINTCUT DECLARATION-OVA, GDE CE SE IGNORISATI SAMO GETERI I SETERI
    @Pointcut("forDaoPackage() && !(forGetters() || forSetters())")
    public void excludeGetterAndSetter(){

    }
}
