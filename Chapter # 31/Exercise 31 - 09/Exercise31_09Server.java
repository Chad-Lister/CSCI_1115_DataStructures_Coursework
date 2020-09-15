/*
  Author:  Chad Lister modified Exercise 31 - 09 Server
  Date:  09/15/2020

  (Chat) Write a program that enables two users to chat. Implement one user as the server and the other as the client.

  The server has two text areas: one for entering text and the other (non-editable) for displaying the history of the conversation. When the user presses the Enter key, the current line is sent to the client and the text area is cleared.

  The client has two text areas: one (non-editable) for displaying the history of the conversation. When the user presses the Enter key, the current line is sent to the server and the text area is cleared. 

  Hint: To send information, add a listener to the TextArea for when the Enter key is clicked. To receive information, create a new thread to handle the sockets, input/output stream constructors, and the while loop.
  Added button since he taught us JavaFX instead of Java.awt.* which handles Text Area listeners.

  When sending and receiving messages you might consider using the the .trim() method to remove unwanted newline characters.
  
*/

import java.io.*;
import java.net.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.*;

public class Exercise31_09Server extends Application {
  private TextArea taServer = new TextArea();
  private TextArea taClient = new TextArea();

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
    
    //  Added by Chad Lister since he didn't discuss Java.awt.*
    Button send = new Button("Send");
    pane2.setBottom(send);
    pane2.setAlignment(send, Pos.BOTTOM_CENTER);
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(pane1, pane2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 200, 200);
    primaryStage.setTitle("Exercise 31 - 09 Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    //  Button request.
    send.requestFocus();
    
    // To complete later.  Added by Chad Lister.
    new Thread(() -> {
      try {
        
        //  Create socket
        ServerSocket serverSocket = new ServerSocket(4000);
        Socket socket = serverSocket.accept();
        DataInputStream serverM = new DataInputStream(socket.getInputStream());
        DataOutputStream clientM = new DataOutputStream(socket.getOutputStream());
        
        //  If a response. Send.
        send.setOnAction(e -> {
          new Thread(() -> {
            try {
              String resp = taClient.getText().trim();
              taServer.appendText("S:  " + resp + "\n");
              clientM.writeUTF(resp);
              clientM.flush();
              taClient.clear();
            }
            catch (IOException ex) {
              ex.printStackTrace();
            }
          }).start();
        });
        
        //  Display server message in history.
        while (true) {

          String mess = serverM.readUTF();
          taServer.appendText("C:  " + mess + "\n");
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
