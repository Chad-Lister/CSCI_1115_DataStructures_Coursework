/**
..* <h1>Account Activity</h1>
..* <p>This class contains the member's or customer's account activity information tied to their main account.</p>
..*
..* <p>***  Currenty not used other than as a file related to ATM.</p>
..*
..* <p>Created:  09/25/2020</p>
..*/

import java.io.*;
import java.util.*;


public class AccountActivity implements java.io.Serializable {
	
	//  Data Fields.
	private int accountId;
	private double amount;
	private transient String typeA;
	private transient String tellerId;
	private transient java.util.Date dateCreated;
	
	//  Default Constructor.
	public AccountActivity() {
	}
	
	//  Constructor with specific fields.
	public AccountActivity(int accountId, double amount, String typeA, String tellerId) {
		this.accountId = accountId;
		this.amount = amount;
		this.typeA = typeA;
		this.tellerId = tellerId;
		dateCreated = new java.util.Date();
	}
	
	//  Methods
	public int getId() {
		return this.accountId;
	}
			
	public String getType() {
		return this.typeA;
	}
		
	public String getTellerId() {
		return this.tellerId;
	}
	
	public Double getAmount() {
		return this.amount;
	}

	public Date getDate() {
		return this.dateCreated;
	}
	
	public Date setDate(Date newDate) {
		dateCreated = newDate;
		return this.dateCreated;
	}

}