# CSCI-1115-FinalProject

## Synopsis
This is a banking simulation using a bank server, files, and ATM screen.

## Motivation
This is a small piece of a larger project that I am planning.

## How to Run
The project contains the following classes:

	1)  Member classes and account activity to simulate bank customer account's and files.
	2)  BuildInitFiles to build initial member, savings, checking and activity files.
	3)  BankServer to represent a banks main server.  Writes to history file.
	4)  ATM to simulate an ATM for customer withdrawals.
	5)  Test Files folder to contain the test files.
	
	Please Note:  The file paths must match.  Please see Read Me.rtf.
	If paths match then simply run BankServer.java and ATM.java.
	
<img align = "left" height = "300" width = "300" src = "Main Login.png">
<img align = "right" height = "300" width = "300" src = "Savings Withdrawal.png">


## Code Example

```
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

```

## Tests
Valid accounts for running tests are 1000 - 1004 with personal identification numbers set to 9999.  See BuildInitFiles for starting values.

