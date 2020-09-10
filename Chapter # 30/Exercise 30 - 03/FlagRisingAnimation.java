/*
	Author:  Chad Lister revised from Listing 15.13.
	Date:  09/08/2020
	
	(Raise flags) Rewrite Listing 15.13 using a thread to animate a flag being raised.

	Download these files to start.
*/

import javafx.animation.PathTransition; 
import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;

public class FlagRisingAnimation extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane
		Pane pane = new Pane();
	
		// Add an image view and add it to pane
		ImageView imageView = new ImageView("image/us.gif");
		pane.getChildren().add(imageView);

		// Create a path transition
		PathTransition pt = new PathTransition(Duration.millis(10000),
							new Line(100, 200, 100, 0), imageView); pt.setCycleCount(5);
							
		//  Create thread to control animation.
		//  Doesn't sleep so no try - catch block needed.
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				//  Start animation.
				pt.play();
			}
			
		//  Start thread.
		}).start();
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 250, 200); 
		primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}