/*
  Author:  Chad Lister modified Exercise 31 - 01 Client.
  Date:  09/11/2020
  
  (Loan server) Write a server for a client. The client sends loan information (annual interest rate, number of years, and loan amount) to the server. The server creates a Loan object to compute monthly payment and total payment, and sends those numbers back to the client.

  Download these files to start.
*/

// Exercise31_01Client.java: The client sends the input to the server and receives
// result back from the server

import java.io.*;
import java.net.*;
import javafx.event.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise31_01Client extends Application {
  // Text field for receiving radius
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfLoanAmount = new TextField();
  private Button btSubmit= new Button("Submit");

  // Text area to display contents
  private TextArea ta = new TextArea();
  
  //  IO Streams.  Added by Chad Lister.
  DataOutputStream toServer;
  DataInputStream fromServer;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Annual Interest Rate"), 0, 0);
    gridPane.add(new Label("Number Of Years"), 0, 1);
    gridPane.add(new Label("Loan Amount"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfLoanAmount, 1, 2);
    gridPane.add(btSubmit, 2, 1);
    
    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
    tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
    
    tfLoanAmount.setPrefColumnCount(5);
    tfNumOfYears.setPrefColumnCount(5);
    tfLoanAmount.setPrefColumnCount(5);
            
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    //  Added by Chad Lister.  Create a server socket, input and output streams.
    try {
      Socket socket = new Socket("localhost", 4000);
      fromServer = new DataInputStream(socket.getInputStream());
      toServer = new DataOutputStream(socket.getOutputStream());
    }
    catch (IOException ex) {
      ta.appendText(ex.toString() + "\n");
    }
    
    //  Added by Chad Lister.  Event Listener for Submit button.
    btSubmit.setOnAction(e -> {
      
      //  Get text field information, convert and send to Server.
      try {
        double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText().trim());
        int numberOfYears = Integer.parseInt(tfNumOfYears.getText().trim());
        double loanAmount = Double.parseDouble(tfLoanAmount.getText().trim());
        
        toServer.writeDouble(annualInterestRate);
        toServer.writeInt(numberOfYears);
        toServer.writeDouble(loanAmount);
        
        //  Display to contents area.
        ta.appendText("Annual Interest Rate:  " + annualInterestRate + "\n");
        ta.appendText("Number of Years:  " + numberOfYears + "\n");
        ta.appendText("Loan Amount:  $ " + loanAmount + "\n");
        
        //   Get payment information from Server.
        double monthlyPayment = fromServer.readDouble();
        double totalPayment = fromServer.readDouble();
        ta.appendText("Monthly Payment:  $ " + monthlyPayment + "\n");
        ta.appendText("Total Payment:  $ " + totalPayment + "\n");
        
      }
      catch (IOException ex) {
        System.err.println(ex);
      }
    });
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
