package com.capgemini.service;

import com.capgemini.Exception.InsufficientAccountOpeningBalanceException;
import com.capgemini.Exception.InsufficientfundException;
import com.capgemini.Exception.InvalidAccountNumberException;
import com.capgemini.beans.Account;

public interface AccountService {
	
	  Account createAccount(int accountNumber,int amount) throws InsufficientAccountOpeningBalanceException;
	  int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException, InsufficientfundException;
	  Account depositeAmount(int accountNumber,int amount) throws InvalidAccountNumberException;
	

}
