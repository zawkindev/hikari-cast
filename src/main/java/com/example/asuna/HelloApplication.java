package com.example.asuna;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static boolean isStreaming = false;
    private static boolean isRecording = false;

    @Override
    public void start(Stage primaryStage) throws IOException {
        HBox MainBox = new HBox(10);

        VBox ButtonGroup = new VBox(10);
        Button Stream = new Button("Start Stream");
        TextField StreamKey = new TextField();
        StreamKey.setPromptText("YouTube Stream key");
        Button Record = new Button("Start Recording");
        ComboBox<String> QualityComboBox = new ComboBox<>();
        QualityComboBox.getItems().addAll("Low", "Medium", "High", "Ultra");
        QualityComboBox.setValue("Choose Video Quality");
        ButtonGroup.getChildren().addAll(Stream, StreamKey, Record, QualityComboBox);
        ButtonGroup.getStyleClass().add("button-group");
        ButtonGroup.setAlignment(Pos.CENTER);
        ButtonGroup.setPadding(new Insets(0, 20, 0, 20));

        Stream.setOnAction(event -> {
            isStreaming = !isStreaming;
            if (isStreaming && !isRecording) {
                Stream.setText("Stop Streaming");
            } else {
                Stream.setText("Start Streaming");
            }
        });

        Record.setOnAction(event -> {
            isRecording = !isRecording;
            if (isRecording && !isStreaming) {
                Record.setText("Stop Recording");
            } else {
                Record.setText("Start Recording");
            }
        });

        MainBox.getChildren().addAll(ButtonGroup);

        Scene scene = new Scene(MainBox, 1200, 700);
        scene.getStylesheets().add("style.css");

        primaryStage.setTitle("Hikari Cast");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}