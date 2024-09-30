package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;

public interface AccountDAO {
    // OVDE PISEM BUSINESS METODE
    void addAccount(Account account, boolean vipFlag);

    boolean doWork();
}
