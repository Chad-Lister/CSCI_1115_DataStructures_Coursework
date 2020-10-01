/**
..* <h1>Build Initial Files</h1>
..* <p>This class builds the initial files needed for the ATM project.</p>
..*
..* <p>Created:  09/25/2020</p>
..*/
	
import java.io.*;

public class BuildInitFiles {
	
	public static void main(String[] args) throws IOException {
		
		//  Build member (customer) file.
		try (
		ObjectOutputStream outMember = new ObjectOutputStream(new FileOutputStream("Test Files/MemberAccount.dat"));
		) {
			MemberAccount m1 = new MemberAccount(1000, 9999, "Bob", "", "Jones", "1111 North Main", "Apt. # 2", "Cedar City", "UT");
			MemberAccount m2 = new MemberAccount(1001, 9999, "Doug", "", "Banks", "23 North 400 East", "", "Cedar City", "UT");
			MemberAccount m3 = new MemberAccount(1002, 9999, "Wendy", "Paula", "Jameson", "44 West Royal Drive", "", "Cedar City", "UT");
			MemberAccount m4 = new MemberAccount(1003, 9999, "Barbara", "", "Douglas", "600 South Main", "Apt. # 4b", "Cedar City", "UT");
			MemberAccount m5 = new MemberAccount(1004, 9999, "Todd", "C.", "Tanner", "2155 Ridgecrest Hills", "", "Cedar City", "UT");
			outMember.writeObject(m1);
			outMember.writeObject(m2);
			outMember.writeObject(m3);
			outMember.writeObject(m4);
			outMember.writeObject(m5);
		}
		catch (java.io.IOException ex) {
			ex.printStackTrace();
		}
		
		//  Build Savings Account for members.
		try (ObjectOutputStream outSavings = new ObjectOutputStream(new FileOutputStream("Test Files/SavingsAccount.dat"));
		) {
			SavingsAccount s1 = new SavingsAccount(1000, 01, 350.00);
			SavingsAccount s2 = new SavingsAccount(1001, 01, 3500.00);
			SavingsAccount s3 = new SavingsAccount(1002, 01, 50115.12);
			SavingsAccount s4 = new SavingsAccount(1003, 01, 150.30);
			SavingsAccount s5 = new SavingsAccount(1004, 01, 128002.53);
			outSavings.writeObject(s1);
			outSavings.writeObject(s2);
			outSavings.writeObject(s3);
			outSavings.writeObject(s4);
			outSavings.writeObject(s5);
		}
		catch (java.io.IOException ex) {
			ex.printStackTrace();
		}
		
		//  Build Checking Account for members.
		try (ObjectOutputStream outChecking = new ObjectOutputStream(new FileOutputStream("Test Files/CheckingAccount.dat"));
		) {
			CheckingAccount c1 = new CheckingAccount(1000, 01, 50.00, 5.00);
			CheckingAccount c2 = new CheckingAccount(1001, 01, 500.00, 25.00);
			CheckingAccount c3 = new CheckingAccount(1002, 01, 1100.02, 25.00);
			CheckingAccount c4 = new CheckingAccount(1003, 01, 15.33, 5.00);
			CheckingAccount c5 = new CheckingAccount(1004, 01, 2230.00, 50.00);

			outChecking.writeObject(c1);
			outChecking.writeObject(c2);
			outChecking.writeObject(c3);
			outChecking.writeObject(c4);
			outChecking.writeObject(c5);
		}
		catch (java.io.IOException ex) {
			ex.printStackTrace();
		}
		
		//  Build Account Activity for members.
		try (ObjectOutputStream outActivity = new ObjectOutputStream(new FileOutputStream("Test Files/AccountActivity.dat"));
		) {
			AccountActivity a1 = new AccountActivity(1002, -20.00, "Checking Withdraw", "ATM");
			AccountActivity a2 = new AccountActivity(1000, 220.00, "Checking Deposit", "01");
			AccountActivity a3 = new AccountActivity(1004, 220.00, "Savings WithDraw", "01");
			AccountActivity a4 = new AccountActivity(1001, -20.00, "Checking Withdraw", "ATM");
			AccountActivity a5 = new AccountActivity(1002, 1220.00, "Savings Deposit", "01");

			outActivity.writeObject(a1);
			outActivity.writeObject(a2);
			outActivity.writeObject(a3);
			outActivity.writeObject(a4);
			outActivity.writeObject(a5);
		}
		catch (java.io.IOException ex) {
			ex.printStackTrace();
		}
	}
}