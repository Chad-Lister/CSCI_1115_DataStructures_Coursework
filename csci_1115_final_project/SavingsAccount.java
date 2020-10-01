/**
..* <h1>SavingsAccount</h1>
..* <p>This class contains the member's or customer's savings account information tied to their main account.</p>
..*
..* <p>Created:  09/25/2020</p>
  */

import java.io.*;
import java.util.*;

public class SavingsAccount implements java.io.Serializable {

	// Attributes
	private int accountId;
	private int accountSuffix;
	private double balance;
	private transient java.util.Date dateCreated;
		
	//  Constructors.
	public SavingsAccount() {
		this(0, 0, 0);
	}
	
	public SavingsAccount(int savingsId, int suffix, double savingsBalance) {
		this.accountId = savingsId;
		this.accountSuffix = suffix;
		this.balance = savingsBalance;
		dateCreated = new java.util.Date();
	}
	
	//  Methods
	public int getId() {
		return this.accountId;
	}

	public int getSuffix() {
		return this.accountSuffix;
	}

	public double getBalance() {
		return this.balance;
	}
	
	public int setId(int account) {
		return this.accountId;
	}

	public double withdraw(double withdrawAmount) {
		return this.balance - withdrawAmount;
	}
	
	public double deposit(double depositAmount) {
		return this.balance + depositAmount;
	}	

}