/**
  * <h1>Bank Server</h1>
  *
  * <p>This class represents the banks' main server.</p>
  *
  * <p>Created:  09/25/2020</P>
  *
  * @author Chad Lister
  */
  
import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class BankServer extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    
    ta.setWrapText(true);
    ta.setEditable(false);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 400, 200);
    primaryStage.setTitle("Main Bank Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    //  Added by Chad Lister.
    new Thread(() -> {
      
      //  Create a socket, listen for connection and create input and output streams.
      try{
        
        ServerSocket serverSocket = new ServerSocket(4000);
        Platform.runLater(() -> ta.appendText("Main Bank Server started at " + new Date() + "\n"));
        Socket socket = serverSocket.accept();
        DataInputStream clientInput = new DataInputStream(socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
        
        //  Receive text field input, make an account activity. 
        while (true) {
          
          int account = clientInput.readInt();
          double amount = clientInput.readDouble();
          String typeA = clientInput.readUTF();
          String teller = clientInput.readUTF();
          AccountActivity activity = new AccountActivity(account, amount, typeA, teller);
          outputToClient.writeDouble(activity.getAmount());
          
          //  Display to content area.
          Platform.runLater(() -> {
            ta.appendText("Connected to a ATM at ... \t" + new Date() + "\n");
            ta.appendText("Activity for Account # " + activity.getId() + " in the amount of $ " + activity.getAmount() + "was written to activity file at ...\t " + activity.getDate() + " by teller " + activity.getTellerId() + activity.getType() + ".\n");
            });
        }
      }
      catch (EOFException ex) {                  // not sure why
        ex.printStackTrace();
      }      
      catch (IOException ex1) {
        ex1.printStackTrace();
      }
    }).start();
    
    //  Close in/out
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
