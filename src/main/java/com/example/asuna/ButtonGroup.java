package com.example.asuna;

import com.example.asuna.utils.ScreenCapture;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.awt.*;

public class ButtonGroup extends VBox {
    private static boolean isStreaming = false;
    private static boolean isRecording = false;
    private ScreenCapture screenCapture;

    public ButtonGroup() {
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("button-group");
        this.setPadding(new Insets(0, 20, 0, 20));

        Button Stream = new Button("Start Stream");
        TextField StreamKey = new TextField();
        StreamKey.setPromptText("YouTube Stream key");
        Button Record = new Button("Start Recording");
        ComboBox<String> QualityComboBox = new ComboBox<>();
        QualityComboBox.getItems().addAll("Low", "Medium", "High", "Ultra");
        QualityComboBox.setValue("Choose Video Quality");

        this.getChildren().addAll(Stream, StreamKey, Record, QualityComboBox);

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
    }

    public void setScreenCapture(ScreenCapture screenCapture) {
        this.screenCapture = screenCapture;
    }
}
