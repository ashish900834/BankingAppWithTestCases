package com.capgemini.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.Exception.InsufficientAccountOpeningBalanceException;
import com.capgemini.Exception.InsufficientfundException;
import com.capgemini.Exception.InvalidAccountNumberException;
import com.capgemini.beans.Account;
import com.capgemini.repository.AccountRepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

import static org.mockito.Mockito.when;
public class AccountTest {
	AccountService service;
	  @Mock
	  AccountRepo repository;
	 
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service=new AccountServiceImpl(repository);
	}
	
	
	@Test(expected=com.capgemini.Exception.InsufficientAccountOpeningBalanceException.class)
    public void whenAmountisLessThanfiveHundredSystemShouldThrowException() throws InsufficientAccountOpeningBalanceException{
	service.createAccount(101,300);
}
	@Test
	public void accountShouldCreateSuccesfully() throws InsufficientAccountOpeningBalanceException{
		Account account=new Account();
		account.setAccountNumber(100);
		account.setAmount(3000);
		when(repository.save(account)).thenReturn(true);
		assertEquals(account,service.createAccount(100,3000));
		}
		
	@Test(expected=com.capgemini.Exception.InvalidAccountNumberException.class)
	public void WhenAccountIsNotThereForDeposite() throws InvalidAccountNumberException{
		service.depositeAmount(101,500);
		}

	
     @Test
	public void WhenTheAmountIsDepositedSuccessfully() throws InvalidAccountNumberException{
       Account account=new Account();
       account.setAccountNumber(101);
       account.setAmount(500);
	    when(repository.search(101)).thenReturn(account);
		assertEquals(account,service.depositeAmount(101,500));
		
	}
     
     @Test(expected=com.capgemini.Exception.InvalidAccountNumberException.class)
     public void WhenTheAccountIsNotThereForWithdrawThenThrowException() throws InvalidAccountNumberException, InsufficientfundException{
     service.withdrawAmount(101,600);
     }
     
     
     @Test(expected=com.capgemini.Exception.InsufficientfundException.class)
     public void WhenAmountisGraterThanAvailableBalanceThenThrowInsufficientFundException() throws InvalidAccountNumberException, InsufficientfundException{
    	Account account=new Account();
    	account.setAccountNumber(101);
    	account.setAmount(500);
    	when(repository.search(account.getAccountNumber())).thenReturn(account);
    	assertEquals(account,service.withdrawAmount(101,800));
    	 
    	 
     }
    
     @Test
     public void WhenTheAmountIsLessThanTheAvailableBalanceThenShowWithdrawalSuccesfully() throws InvalidAccountNumberException, InsufficientfundException{
    	 Account account=new Account();
    	 account.setAccountNumber(102);
     	account.setAmount(5000);
     	when(repository.search(account.getAccountNumber())).thenReturn(account);
     	assertEquals(4200,service.withdrawAmount(102,800));
     }

}
