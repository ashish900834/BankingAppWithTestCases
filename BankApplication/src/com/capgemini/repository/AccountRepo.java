package com.capgemini.repository;

import com.capgemini.beans.Account;

public interface AccountRepo {
	
	public boolean save(Account account);
	public Account search(int accountNumber);

}
