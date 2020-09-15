/*
  Author:  Chad Lister modified Exercise 31 - 01 Server.
  Date:  09/11/2020
  
  (Loan server) Write a server for a client. The client sends loan information (annual interest rate, number of years, and loan amount) to the server. The server creates a Loan object to compute monthly payment and total payment, and sends those numbers back to the client.

  Download these files to start.
*/

// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise31_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    //  Added by Chad Lister.
    new Thread(() -> {
      
      //  Create a socket, listen for connection and create input and output streams.
      try{
        
        ServerSocket serverSocket = new ServerSocket(4000);
        Platform.runLater(() -> ta.appendText("Exercise 31 - 01 Server started at " + new Date() + "\n"));
        Socket socket = serverSocket.accept();
        DataInputStream clientInput = new DataInputStream(socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
        
        //  Receive field input, make Loan object and send payment information.
        while (true) {
          
          double interest = clientInput.readDouble();
          int years = clientInput.readInt();
          double loanAmount = clientInput.readDouble();
          Loan l = new Loan(interest, years, loanAmount);
          outputToClient.writeDouble(l.getMonthlyPayment());
          outputToClient.writeDouble(l.getTotalPayment());
          
          //  Display to content area.
          Platform.runLater(() -> {
            ta.appendText("Connected to a client at " + new Date() + "\n");
            ta.appendText("Annual Interest Rate:  " + l.getAnnualInterestRate() + "\n");
            ta.appendText("Number of Years:  " + l.getNumberOfYears() + "\n");
            ta.appendText("Loan Amount:  $ " + l.getLoanAmount() + "\n");
            ta.appendText("Monthly Payment:  $ " + l.getMonthlyPayment() + "\n");
            ta.appendText("Total Payment:  $ " + l.getTotalPayment() + "\n");
            });
        }
      }
      catch (IOException ex) {
        ex.printStackTrace();
      }
    }).start();
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
