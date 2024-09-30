package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO){
		return runner -> {
			//demoTheBeforeAdvice(accountDAO, membershipDAO);

			//demoAfterReturningAdvice(accountDAO);

			//demoAfterThrowingAdvice(accountDAO);

			//demoAfterFinallyAdvice(accountDAO);
		};
	}

	private void demoAfterFinallyAdvice(AccountDAO accountDAO) {
		List<Account> accounts = null;

		try{
			// SIMULIRAM EXCEPTION THROW, AKO JE TRUE ONDA NEKA BACI
			boolean flag = false;

			accounts = accountDAO.findAccounts(flag); //ASPECT CE U OVOJ LINIJI DA DOBIJE REZULTAT METODE
		}
		catch (Exception exception){
			System.out.println("Main program: ... caught exception: " + exception);
		}

		System.out.println("---Main program: demoAfterFinallyAdvice---");
		System.out.println("The accounts: " + accounts);
	}

	private void demoAfterThrowingAdvice(AccountDAO accountDAO) {

		List<Account> accounts = null;

		try{
			// SIMULIRAM EXCEPTION THROW, AKO JE TRUE ONDA NEKA BACI
			boolean flag = true;

			accounts = accountDAO.findAccounts(flag); //ASPECT CE U OVOJ LINIJI DA DOBIJE REZULTAT METODE
		}
		catch (Exception exception){
			System.out.println("Main program: ... caught exception: " + exception);
		}

		System.out.println("---Main program: demoAfterThrowingAdvice---");
		System.out.println("The accounts: " + accounts);
	}

//	private void demoAfterReturningAdvice(AccountDAO accountDAO) {
//		List<Account> accounts = accountDAO.findAccounts(); //ASPECT CE U OVOJ LINIJI DA DOBIJE REZULTAT METODE
//		System.out.println("---Main program: demoAfterReturningAdvice---");
//		System.out.println("The accounts: " + accounts);
//	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		accountDAO.addAccount(new Account("bogdan", "intermediate"), true);
		accountDAO.doWork();

		accountDAO.setName("test");
		accountDAO.setServiceCode("example1");

		accountDAO.getName();
		accountDAO.getServiceCode();

		membershipDAO.addSillyMember();
		membershipDAO.goToSleep();
	}
}
