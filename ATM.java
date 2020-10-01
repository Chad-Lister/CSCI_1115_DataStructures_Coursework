/**
  * <h1>ATM</h1>
  *
  * <p>This class represents the bank's ATM.  Customers or members can either withdrawalal from savings or checking.</p>
  *
  * <p>Created:  09/25/2020</P>
  *
  * @author Chad Lister
  */

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ContentDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;import java.net.*;

public class ATM extends Application {
  
  //  Field for Communications.
  private TextArea ta = new TextArea();
  private ScrollPane com = new ScrollPane();
  private Map<Integer, Integer> memberMap = new HashMap<Integer,Integer>();
  private Map<Integer, Double> savingsMap = new HashMap<Integer, Double>();
  private Map<Integer, Double> checkingMap = new HashMap<Integer, Double>();
  private DataOutputStream toServer;
  private DataInputStream fromServer;
    
  @Override // Override the start method in the Application class
  // public void start(Stage primaryStage) throws NumberFormatException {
  public void start(Stage primaryStage) {
        
    ta.setEditable(false);
    ta.setWrapText(true);
    ta.setPrefSize(300, 300);
    
    HBox bankHeader = new HBox();
    Text bank = new Text("Bank of Java");
    bank.setStyle("-fx-font-size: 2em");
    bank.setStroke(Color.RED);
    bankHeader.getChildren().add(bank);
    bankHeader.setAlignment(Pos.TOP_CENTER);

    // Create a pane and set its properties
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);
    pane.setPrefSize(500, 500);

    // Place nodes in the pane
    pane.add(new Label("Account Number:  "), 0, 0);
    TextField accountNumber = new TextField();
    pane.add(accountNumber, 1, 0);
    pane.add(new Label("Personal ID Number: "), 0, 1);
    TextField pidNumber = new TextField();
    pane.add(pidNumber, 1, 1);
    Button accountLogin = new Button("Account Login");
    pane.add(accountLogin, 2, 2);
    
    //  Button Listener.
    accountLogin.setOnAction(e -> {
      try {
        String a = accountNumber.getText().trim();
        String p = pidNumber.getText().trim();
        int account = Integer.parseInt(a);
        int pid = Integer.parseInt(p);
        verify(account, pid);
      }
      catch (NumberFormatException ex) {
        ta.appendText("***  Invalid entry.  Numbers required.  ***");
        accountNumber.clear();
        pidNumber.clear();
      }
      catch (EOFException ex1) {
//        ta.appendText("End of file!");
 //       primaryStage.close();
      }
      catch (FileNotFoundException ex2) {
        ta.appendText("File Not Found!");
      }
      catch (IOException ex3) {
        ex3.printStackTrace();
      }
      catch (ClassNotFoundException ex4) {
        ex4.printStackTrace();
      }  
    });

    BorderPane atmScreen = new BorderPane();
    ScrollPane com = new ScrollPane(ta);
    atmScreen.setRight(com);
    atmScreen.setCenter(pane);
    atmScreen.setTop(bankHeader);
    atmScreen.setPrefSize(700, 700);

    // Create a scene and place it in the stage
    Scene scene = new Scene(atmScreen);
    primaryStage.setTitle("ATM Login"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    accountLogin.requestFocus();
  }
  
  /** 
  * Verifies the account number and personal identification number previously entered matches what is in the Member or Customer main file.
  * 
  * @param account (int; The account number entered in the previous screen.)
  * @param pid (int; The personal identification number entered in the previous screen.)
  */
    
  public void verify(int account, int pid) throws EOFException, FileNotFoundException, IOException, ClassNotFoundException {
    
    ta.appendText("Verifying identification...\n");
    
    //  Read account numbers from Members (Customers) file.
    try (ObjectInputStream file = new ObjectInputStream(new FileInputStream("Test Files/MemberAccount.dat"));
    ) {
      
      //  While not End of File.
      while (true) {
        MemberAccount acct = (MemberAccount) file.readObject();
        int acc = acct.getId();
        int memPid = acct.getPid();
        memberMap.put(acc, memPid);
        
        //  If entered fields match file numbers.
        if (acc == account && pid == memPid) {
          
          //  Invoke atmSelection screen.
          atmSelection(account);
        }
//        else if (c <= 3) {                                                              ***  Does not do security.
//          ta.appendText("Account information is Invalid!  Please try again.");          *** asks several
//          c++;
//          break;
//        }
//        else {
//          //  Go back to main atm.
//          
//          break;
//        }
      }
    }
    catch (EOFException ex1) {
    }
    catch (FileNotFoundException ex2) {
    ta.appendText("Please see teller.\n");
    ta.appendText("File Not Found!\n");
    }
    catch (IOException ex3) {
      ex3.printStackTrace();
    }
    catch (ClassNotFoundException ex4) {
      ex4.printStackTrace();
    }
    
    //  Print out to terminal.
    Set<Integer> keySet = memberMap.keySet();
    ArrayList<Integer> keyList = new ArrayList<Integer>(keySet);
    Collection<Integer> values = memberMap.values();
    ArrayList<Integer> valueList = new ArrayList<>(values);
    System.out.println("  ***  Member Map contains  ***  ");
    for (int i = 0; i < keyList.size(); i++) {
      System.out.println("\t  " + keyList.get(i) + "  " + valueList.get(i));
    }
  }
  
  /** 
  * Shows the ATM user the withdrawal selection screen.  They can either withdrawal from savings or checking.
  *  
  * @param account (int; The account number matched to member number.)
  */
  
  public void atmSelection(int account) {
    
    Stage select = new Stage();
    
    HBox accountHeader = new HBox();
    Text acc = new Text("Withdrawal from Account #:  " + account);
    acc.setStyle("-fx-font-size: 1em");
    acc.setStroke(Color.BLACK);
    accountHeader.getChildren().add(acc);
    accountHeader.setAlignment(Pos.TOP_LEFT);
        
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);
    pane.setPrefSize(500, 500);

    pane.add(new Label("1 - Savings Withdrawal:  "), 0, 0);
    pane.add(new Label("2 - Checking Withdrawal: "), 0, 1);
    pane.add(new Label("Enter Choice:  "), 0, 3);
    TextField choice = new TextField();
    pane.add(choice, 1, 3);
    
    //  Choose between savings or checking.
    choice.setOnAction(e -> {

      int c = Integer.parseInt(choice.getText());
      if (c == 1) {
        //  Invoke savings withdrawal and close stage.
        savingsWithdraw(account);
        select.close();
      }
      else if (c == 2) {
        // Invoke checking withdrawal and close stage.
        checkingWithdraw(account);
        select.close();
      }
      else {
        ta.appendText("Invalid Choice!");
      }
    });
    
    BorderPane selectScreen = new BorderPane();
    selectScreen.setRight(ta);
    selectScreen.setCenter(pane);
    selectScreen.setTop(accountHeader);
    selectScreen.setPrefSize(700, 700);
    
    Scene scene = new Scene(selectScreen);
    select.setTitle("Selection"); // Set the stage title
    select.setScene(scene); // Place the scene in the stage
    select.show(); // Display the stage
    
    choice.requestFocus();

  }
  
  /** 
  *  The savings withdrawal screen.  Takes the amount field and processes withdrawal.
  *  
  * @param account (int; The account number to process the withdrawal for.)
  */
  
  public void savingsWithdraw(int account) {
    
    try {
      Socket socket = new Socket("localhost", 4000);
      fromServer = new DataInputStream(socket.getInputStream());
      toServer = new DataOutputStream(socket.getOutputStream());
      ta.appendText("Connected to bank server at " + new Date() + "\n");
    }
    catch (IOException ex) {
      ta.appendText(ex.toString() + "\n");
    }
        
    ta.setEditable(false);
    ta.appendText("Savings withdrawal screen for account # " + account + "\n");

    Stage savingsStage = new Stage();
    
    HBox accountHeader = new HBox();
    Text acc = new Text("Withdrawal from Account #:  " + account);
    acc.setStyle("-fx-font-size: 1em");
    acc.setStroke(Color.BLACK);
    accountHeader.getChildren().add(acc);
    accountHeader.setAlignment(Pos.TOP_LEFT);
        
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);
    pane.setPrefSize(500, 500);

    //  Withdrawal text field.
    pane.add(new Label("Withdrawal from Savings"), 0, 0);
    pane.add(new Label("Amount:  $"), 0, 1);
    TextField withdrawField = new TextField();
    pane.add(withdrawField, 1, 1);

    //  Text field listener.
    withdrawField.setOnAction(e -> {
      
      //  Read from savings account file.
      try (ObjectInputStream file = new ObjectInputStream(new FileInputStream("Test Files/SavingsAccount.dat"));
      ) {
        
        //  While not end of file.
        while (true) {
          SavingsAccount acct = (SavingsAccount) file.readObject();
          int accN = acct.getId();
          double balance = acct.getBalance();
          savingsMap.put(accN, balance);

          //  If account number matches.
          if (accN == account) {
            
            //  System print map to terminal.
            Set<Integer> keySet = savingsMap.keySet();
            ArrayList<Integer> keyList = new ArrayList<Integer>(keySet);
            Collection<Double> values = savingsMap.values();
            ArrayList<Double> valueList = new ArrayList<>(values);
            System.out.println("\n  ***  Savings Map contains before withdrawal ***  ");
            for (int i = 0; i < keyList.size(); i++) {
              System.out.println("\t  " + keyList.get(i) + "  " + valueList.get(i));
            }         
            
            //  Get text field values and process, append text area.
            String withdrawAmount = withdrawField.getText();
            double withdrawnAmount = Double.parseDouble(withdrawAmount);
            ta.appendText("Available Balance:  $ " + acct.getBalance() + "\n");
            ta.appendText("                 :  - " + withdrawnAmount + "\n");
            double remaining = acct.withdraw(withdrawnAmount);
            ta.appendText("Remaining Balance is:  $" + remaining + "\n");
            savingsMap.replace(accN, remaining);
            
            //  Print out to terminal.
            Set<Integer> keySet2 = savingsMap.keySet();
            ArrayList<Integer> keyList2 = new ArrayList<Integer>(keySet2);
            Collection<Double> values2 = savingsMap.values();
            ArrayList<Double> valueList2 = new ArrayList<>(values2);
            System.out.println("\n  ***  Savings Map contains after withdrawal ***  ");
            for (int i = 0; i < keyList2.size(); i++) {
              System.out.println("\t  " + keyList2.get(i) + "  " + valueList2.get(i));
            }
            try {
              ta.appendText("\nActivity sent to bank server on " + new Date() + "\n");
              toServer.writeInt(accN);
              toServer.writeDouble(withdrawnAmount);
              toServer.writeUTF("Savings Withdrawal");
              toServer.writeUTF("ATM");
              
              double withdrawn = fromServer.readDouble();
              ta.appendText("An account withdrawal of :  $ " + withdrawn + " was written to activity file.\n");
            }
            catch (IOException s) {
              ta.appendText("Server connection lost!");
            }
          }
          else {
//            break;
          }
        }
      }
      catch (EOFException ex1) {
      }
      catch (FileNotFoundException ex2) {
        ta.appendText("File Not Found!");
      }
      catch (IOException ex3) {
        ex3.printStackTrace();
      }
      catch (ClassNotFoundException ex4) {
        ex4.printStackTrace();
      }
    });
    
    BorderPane savingsScreen = new BorderPane();
    savingsScreen.setRight(ta);
    savingsScreen.setCenter(pane);
    savingsScreen.setTop(accountHeader);
    savingsScreen.setPrefSize(700, 700);
    
    Scene scene = new Scene(savingsScreen);
    savingsStage.setTitle("Savings Withdrawal"); // Set the stage title
    savingsStage.setScene(scene); // Place the scene in the stage
    savingsStage.show(); // Display the stage
    
    withdrawField.requestFocus();

  }

  /** 
  *  The checking withdraw screen.  Takes the amount field and processes withdraw.
  * 
  * @param account (int; The account number to process withdraw for.)
  */
  public void checkingWithdraw(int account) {
    
    try {
      Socket socket = new Socket("localhost", 4000);
      fromServer = new DataInputStream(socket.getInputStream());
      toServer = new DataOutputStream(socket.getOutputStream());
      ta.appendText("Connected to bank server at " + new Date() + "\n");
    }
    catch (IOException ex) {
      ta.appendText(ex.toString() + "\n");
    }
    
    ta.appendText("Checking withdrawal for account # " + account + "\n");
    ta.setEditable(false);
        
    Stage checkingStage = new Stage();
    
    HBox accountHeader = new HBox();
    Text acc = new Text("Withdrawal from Account #:  " + account);
    acc.setStyle("-fx-font-size: 1em");
    acc.setStroke(Color.BLACK);
    accountHeader.getChildren().add(acc);
    accountHeader.setAlignment(Pos.TOP_LEFT);
          
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);
    pane.setPrefSize(500, 500);

    pane.add(new Label("Withdrawal from Checking"), 0, 0);
    pane.add(new Label("Amount:  $"), 0, 1);
    TextField withdrawField = new TextField();
    pane.add(withdrawField, 1, 1);

    //  Text field listener.
    withdrawField.setOnAction(e -> {
      
      
      //  Read from checking account file.
      try (ObjectInputStream file = new ObjectInputStream(new FileInputStream("Test Files/CheckingAccount.dat"));
      ) {
        
        //  While not end of file.
        while (true) {
          CheckingAccount acct = (CheckingAccount) file.readObject();
          int accN = acct.getId();
          double balance = acct.getBalance();
          checkingMap.put(accN, balance);
          
          //  If account found.
          if (accN == account) {
            
            //  System print map to terminal.
            Set<Integer> keySet = checkingMap.keySet();
            ArrayList<Integer> keyList = new ArrayList<Integer>(keySet);
            Collection<Double> values = checkingMap.values();
            ArrayList<Double> valueList = new ArrayList<>(values);
            System.out.println("  ***  Checking Map contains before withdrawal  ***  ");
            for (int i = 0; i < keyList.size(); i++) {
              System.out.println("\t  " + keyList.get(i) + "  " + valueList.get(i));
            }
            
            //  Get text field, process withdraw and append text area.
            String withdrawAmount = withdrawField.getText().trim();
            double withdrawnAmount = Double.parseDouble(withdrawAmount);
            ta.appendText("Available Balance:  $ " + acct.getBalance() + "\n");
            ta.appendText("                 :  - " + withdrawnAmount + "\n");
            acct.withdraw(withdrawnAmount);
            double newBalance = acct.getBalance() + acct.getOverDraftLimit() - withdrawnAmount;
            ta.appendText("Remaining Balance is:  $ " + newBalance + "\n");
            checkingMap.replace(accN, newBalance);
            
            //  Print out to terminal.
            Set<Integer> keySet2 = checkingMap.keySet();
            ArrayList<Integer> keyList2 = new ArrayList<Integer>(keySet2);
            Collection<Double> values2 = checkingMap.values();
            ArrayList<Double> valueList2 = new ArrayList<>(values2);
            System.out.println("  ***  Checking Map contains after withdrawal  ***  ");
            for (int i = 0; i < keyList2.size(); i++) {
              System.out.println("\t  " + keyList2.get(i) + "  " + valueList2.get(i));
            }
            //  Send to server.
            try {
              ta.appendText("\nActivity sent to bank server on " + new Date() + "\n");
              toServer.writeInt(accN);
              toServer.writeDouble(withdrawnAmount);
              toServer.writeUTF("Checking Withdrawal");
              toServer.writeUTF("ATM");
              
              double withdrawn = fromServer.readDouble();
              ta.appendText("An account withdrawal of :  $ " + withdrawn + "was written to activity file.\n");
            }
            catch (IOException s) {
              ta.appendText("Server connection lost!");
            }
                          
          }
          else {
//             break;
          }
          }
        }
        catch (EOFException ex1) {
        }        
        catch (FileNotFoundException ex2) {
          ta.appendText("File Not Found!");
        }
        catch (IOException ex3) {
          ex3.printStackTrace();
        }
        catch (ClassNotFoundException ex4) {
          ex4.printStackTrace();
        }
      });
      
      BorderPane checkingScreen = new BorderPane();
      checkingScreen.setRight(ta);
      checkingScreen.setCenter(pane);
      checkingScreen.setTop(accountHeader);
      checkingScreen.setPrefSize(700, 700);
      
      Scene scene = new Scene(checkingScreen);
      checkingStage.setTitle("Checking Withdrawal"); // Set the stage title
      checkingStage.setScene(scene); // Place the scene in the stage
      checkingStage.show(); // Display the stage

  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 