package edu.sdccd.cisc;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label header = new Label("Message Board");
        header.setFont(new Font(20));
        header.setStyle("-fx-font-weight: bold");
        header.setAlignment(Pos.CENTER);

        Label status = new Label("Ready");
        TextField message = new TextField();
        message.setPromptText("Enter Message");
        Label prompt = new Label("Write your message: ");
        Button addBtn = new Button("Add");
        Button resetBtn = new Button("Reset");
        TextArea messagesLog = new TextArea();
        messagesLog.setEditable(false);

        Label wordCount = new Label("");
        // - If input text is empty or blank: set status to "Nothing to add" and return.
        // - Otherwise: append text to history (one line per message),
        //              clear the input field,
        //              set status to "Last action: added message".

        addBtn.setOnAction(e -> {
            if (message.getText().isBlank()) {
                status.setText("Nothing to add");
                message.setText("");
            } else {
                status.setText("Last action: added message");
                wordCount.setText("Last input length: " + message.getText().length());
                messagesLog.appendText(message.getText() + "\n");
                message.setText("");
            }
        });
        // - Clear history and input.
        // - Set status to "Cleared".
        resetBtn.setOnAction(e -> {
            messagesLog.setText("");
            message.setText("");
            status.setText("Last Action: cleared board");
            wordCount.setText("");
        });

        HBox buttonBar = new HBox( 40, addBtn, resetBtn);
        buttonBar.setAlignment(Pos.CENTER);
        VBox topContainer = new VBox(20, header, status);
        VBox messagesBox =  new VBox(20, messagesLog, prompt, message, wordCount);
        messagesBox.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setTop(topContainer);
        root.setCenter(messagesBox);
        root.setBottom(buttonBar);

        Scene scene = new Scene(root, 450, 300);
        stage.setMinWidth(400);
        stage.setMinHeight(250);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Message Board");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
