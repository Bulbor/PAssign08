package Chapt14;

//C:\Users\brode\OneDrive\Documents\GitHub\hello-world

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PAssign08 extends Application {

    // Custom KeyPadPane class
    public class KeyPadCustomPane extends KeyPadPane {
        private TextField pinField;
        private Label messageLabel;

        public KeyPadCustomPane(TextField pinField, Label messageLabel) {
            this.pinField = pinField;
            this.messageLabel = messageLabel;
            // Override the event handlers to add custom functionality
            registerEventHandlers();
        }

        @Override
        protected void registerEventHandlers() {
            super.registerEventHandlers(); // Call the parent method to keep existing functionality

            // Add custom actions for the buttons
            for (Button button : listButtons) {
                button.setOnAction(e -> {
                    String buttonText = button.getText();
                    if ("0".equals(buttonText) || "1".equals(buttonText) || "2".equals(buttonText) ||
                        "3".equals(buttonText) || "4".equals(buttonText) || "5".equals(buttonText) ||
                        "6".equals(buttonText) || "7".equals(buttonText) || "8".equals(buttonText) ||
                        "9".equals(buttonText)) {
                        pinField.appendText(buttonText); // Add number to the PIN field
                    } else if ("#".equals(buttonText)) {
                        // Simulate entering the PIN
                        messageLabel.setText("Entered PIN: " + pinField.getText());
                    } else if ("*".equals(buttonText)) {
                        // Clear the PIN field
                        pinField.clear();
                        messageLabel.setText("PIN cleared.");
                    }
                });
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        VBox vbox = new VBox(10); // Vertical box layout with spacing

        Label instructionLabel = new Label("Enter your PIN:");
        TextField pinField = new TextField();
        pinField.setPromptText("PIN");

        Label messageLabel = new Label(); // Label to display messages
        KeyPadCustomPane keyPad = new KeyPadCustomPane(pinField, messageLabel); // Use custom keypad

        vbox.getChildren().addAll(instructionLabel, pinField, messageLabel); // Add components to VBox
        mainPane.setCenter(keyPad); // Set the keypad in the center of the BorderPane
        mainPane.setBottom(vbox); // Add the VBox at the bottom

        Scene scene = new Scene(mainPane, 400, 400);
        primaryStage.setTitle("ATM Keypad Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}