package com.capgemini.service;

import com.capgemini.Exception.InsufficientAccountOpeningBalanceException;
import com.capgemini.Exception.InsufficientfundException;
import com.capgemini.Exception.InvalidAccountNumberException;
import com.capgemini.beans.Account;
import com.capgemini.repository.AccountRepo;

public class AccountServiceImpl implements AccountService {
   Account account=new Account();

   AccountRepo accountRepo;
	public AccountServiceImpl(AccountRepo accountRepo) {
	
	this.accountRepo = accountRepo;
}

	@Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientAccountOpeningBalanceException {
		if(amount<500){
			throw new InsufficientAccountOpeningBalanceException();
		}
		
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(accountRepo.save(account)){
			return account;
		}
		
		
		return null;
	}
	

	
	public int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException, InsufficientfundException
	{
		Account account=accountRepo.search(accountNumber);
if(account==null){
	throw new InvalidAccountNumberException();
}
		
		if((account.getAmount()-amount)>=0)
		{
			account.setAmount(account.getAmount()-amount);
			return account.getAmount();
		}
		
		throw new InsufficientfundException();
	}
	
	public Account depositeAmount(int accountNumber,int amount) throws InvalidAccountNumberException{
		Account account=accountRepo.search(accountNumber);
		if(account==null){
			throw new InvalidAccountNumberException();
		}
		account.setAccountNumber(accountNumber);
		account.setAmount(account.getAmount()+amount);
		accountRepo.save(account);
		return account;
		
		
			
		}
	}



