/*
  Author:  Chad Lister modified Exercise 31 - 09 Client
  Date:  09/15/2020
  
  (Chat) Write a program that enables two users to chat. Implement one user as the server and the other as the client.

  The server has two text areas: one for entering text and the other (non-editable) for displaying the history of the conversation. When the user presses the Enter key, the current line is sent to the client and the text area is cleared.

  The client has two text areas: one (non-editable) for displaying the history of the conversation. When the user presses the Enter key, the current line is sent to the server and the text area is cleared. 

  Download these files to start.

  Hint: To send information, add a listener to the TextArea for when the Enter key is clicked. To receive information, create a new thread to handle the sockets, input/output stream constructors, and the while loop.
  
  **  Added send button since he taught us JavaFX not Java.awt.* which TextArea listeners are handled in.

  When sending and receiving messages you might consider using the the .trim() method to remove unwanted newline characters.
  
*/

import java.io.*;
import java.net.*;
import javafx.event.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.*;

public class Exercise31_09Client extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();
  
  //  Added by Chad Lister.  IO streams.
  DataOutputStream outToServer;
  DataInputStream inFromServer;
   
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    taServer.setWrapText(true);
    taClient.setWrapText(true);
    taServer.setDisable(true);

    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("History"));
    pane1.setCenter(new ScrollPane(taServer));
    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("New Message"));
    pane2.setCenter(new ScrollPane(taClient));
    
    //  Added by Chad Lister since he never discussed Java.awt.*
    Button send = new Button("Send");
    pane2.setBottom(send);
    pane2.setAlignment(send, Pos.BOTTOM_CENTER);
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise 31 - 09 Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    //  Button request.
    send.requestFocus();

    // To complete later.  Added by Chad Lister.  Button listener/handler.
    send.setOnAction(e -> {
      
      new Thread(() -> {
        
        try {
          //  Get text string.  Send to server and display in history.
          String client = taClient.getText().trim();
          outToServer.writeUTF(client);
          taServer.appendText("C:  " + client + "\n");
          taClient.clear();
          outToServer.flush();
        }
        catch (IOException ex) {
          System.err.println(ex);
        }
      }).start();
    });
    
    new Thread(() -> {
      //  Create socket for server.
      try {
        //  Establish connection.
        Socket socket = new Socket("localhost", 4000);
        outToServer = new DataOutputStream(socket.getOutputStream());
        inFromServer = new DataInputStream(socket.getInputStream());
      
        while (true) {

          //  Get message from server and display in history.
          String server = inFromServer.readUTF();
          taServer.appendText("S:  " + server + "\n");
        }
      }
      catch (IOException ex ) {
        taServer.appendText(ex.toString().trim() + "\n");
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
