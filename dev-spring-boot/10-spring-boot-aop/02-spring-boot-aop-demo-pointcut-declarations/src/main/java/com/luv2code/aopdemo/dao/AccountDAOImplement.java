package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImplement implements AccountDAO {

    private String name;
    private String serviceCode;

    public String getName() {
        System.out.println("Calling getName() for accountDAOImplement");
        return name;
    }

    public void setName(String name) {
        System.out.println("Calling setName() for accountDAOImplement");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("Calling getServiceCode() for accountDAOImplement");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("Calling setServiceCode() for accountDAOImplement");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts(boolean flag) {

        if(flag){
            throw new RuntimeException("RunTimeException thrown!!!!!");
        }

        List<Account> list = new ArrayList<>();

        Account acc1 = new Account("wayne", "admin");
        Account acc2 = new Account("john", "manager");
        Account acc3 = new Account("bruce", "employee");

        list.add(acc1);
        list.add(acc2);
        list.add(acc3);

        return list;
    }

    @Override
    public List<Account> findAccounts() {
//        List<Account> list = new ArrayList<>();
//
//        Account acc1 = new Account("wayne", "admin");
//        Account acc2 = new Account("john", "manager");
//        Account acc3 = new Account("bruce", "employee");
//
//        list.add(acc1);
//        list.add(acc2);
//        list.add(acc3);
//
//        return list;

        return findAccounts(false);
    }

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");

        return false;
    }
}
