/**
..* <h1>CheckingAccount</h1>
..* <p>This class contains the member's or customer's checking account information tied to their main account.</p>
..*
..* <p>Created:  09/25/2020</p>
..*/

import java.io.*;
import java.util.*;

public class CheckingAccount implements java.io.Serializable {
	
	//  Attributes
	private int accountId;
	private int accountSuffix;
	private double balance;
	private transient double overDraftLimit;
	private transient java.util.Date dateCreated;
	
	//  Constructors
	public CheckingAccount() {
		this(0, 0, 0, 0);
	}
	
	public CheckingAccount(int checkingId, int suffix, double checkingBalance, double overDraftLimit) {
		this.accountId = checkingId;
		this.accountSuffix = suffix;
		this.balance = checkingBalance;
		dateCreated = new java.util.Date();
		this.overDraftLimit = overDraftLimit;
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
		return this.balance + this.overDraftLimit - withdrawAmount;
	}
	
	public double deposit(double depositAmount) {
		return this.balance + depositAmount;
	}	
	
	public double getOverDraftLimit() {
		return this.overDraftLimit;
	}

}