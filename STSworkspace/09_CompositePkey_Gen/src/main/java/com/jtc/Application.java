package com.jtc;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jtc.entity.Account;
import com.jtc.entity.AccountPK;
import com.jtc.repository.AccountRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		AccountRepository accRepo  = ctxt.getBean(AccountRepository.class);
		AccountPK pk=new AccountPK();// Here i am setting p key values
//		pk.setAccId(2);
//		pk.setAcctype("Current");
//		pk.setAccnum(125569l);
//		
//		Account acc = new Account();
//		acc.setHolderName("ANUJ");
//		acc.setBranch("Vasant Vihar");
//		acc.setAccountPk(pk);// entered p key values to Account
//		
//		accRepo.save(acc);
		
		// now i want to retrieve a record
		AccountPK pk1 = new AccountPK();
		pk1.setAccId(2);
		pk1.setAccnum(125569l);
		pk1.setAcctype("Current");
		Optional <Account> optional = accRepo.findById(pk1);
		System.out.println(optional.get());
		
	}

}
