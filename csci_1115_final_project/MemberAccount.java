/**
  * <h1>MemberAccount</h1>
..* <p>This class contains the member's or customer's personal information tied to their main account.</p>
..*
..* <p>Created:  09/25/2020</p>
  * @author Chad Lister
  */

import java.util.Date;
import java.io.*;

public class MemberAccount implements java.io.Serializable {
	
	//  Data Fields.
	
	private int accountId;
	private int pid;
	private transient String firstName;
	private transient String middleInit;
	private transient String lastName;
	private transient String add1;
	private transient String add2;
	private transient String city;
	private transient String state;
	private transient java.util.Date dateCreated;
	
	//  Default Constructor.
	public MemberAccount() {
	}
	
	//  Constructor with specific fields.
	public MemberAccount(int accountId, int pid, String fName, String miName, String lName, String line1, String line2, String city, String state) {
		this.accountId = accountId;
		this.pid = pid;
		this.firstName = fName;
		this.middleInit = miName;
		this.lastName = lName;
		this.add1 = line1;
		this.add2 = line2;
		this.city = city;
		this.state = state;
		dateCreated = new java.util.Date();
	}
	
	//  Methods
	public int getId() {
		return this.accountId;
	}

	public int getPid() {
		return this.pid;
	}

		
	public String getFirstName() {
		return this.firstName;
	}

		
	public String getMiddleName() {
		return this.middleInit;
	}

		
	public String getLastName() {
		return this.lastName;
	}
	
	public String getAdd1() {
		return this.add1;
	}

	public String getAdd2() {
		return this.add2;
	}
	
	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public java.util.Date getDateCreated() {
		return this.dateCreated;
	}
	
	public void setId(int accountId) {
		this.accountId = accountId;
		return;
	}

}