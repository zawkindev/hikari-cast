package com.example.asuna;

import com.example.asuna.utils.ScreenCapture;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    private int WIDTH;
    private int HEIGHT;

    @Override
    public void start(Stage primaryStage) throws IOException {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle screenSize = ge.getMaximumWindowBounds();
        WIDTH = (int) screenSize.getWidth();
        HEIGHT = (int) screenSize.getHeight();

        HBox MainBox = new HBox(10);
        ImageView imageView = new ImageView();
        imageView.getStyleClass().add("image-view");
        ScreenCapture screenCapture = new ScreenCapture(imageView, 900, 600, 30);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.setScreenCapture(screenCapture);

        screenCapture.start(WIDTH, HEIGHT, 30);
        StackPane video = new StackPane(imageView);

        MainBox.getChildren().addAll(video, buttonGroup);

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