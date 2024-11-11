package com.example.asuna.utils;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenCapture {
    private int WIDTH;
    private int HEIGHT;
    private int FRAME_RATE;
    private WritableImage writableImage;

    public ScreenCapture(ImageView imageView, int width, int height, int frameRate) {
        WIDTH = width;
        HEIGHT = height;
        FRAME_RATE = frameRate;


        writableImage = new WritableImage(WIDTH, HEIGHT);
        imageView.setImage(writableImage);
    }

    public void start(int width, int height, int frame_rate) {
        // Start a new thread to capture the screen and update the ImageView
        Thread captureThread = new Thread(() -> {
            try {
                FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("desktop");
                grabber.setFormat("gdigrab");  // "x11grab" for Linux, "avfoundation" for macOS
                grabber.setImageWidth(1920);
                grabber.setImageHeight(1080);
                grabber.setFrameRate(60);
                grabber.setOption("buffer_size", "512K");  // Adjust to lower or higher values based on testing

                grabber.start();
                Java2DFrameConverter converter = new Java2DFrameConverter();

                while (true) {
                    System.out.println("running");
                    Frame frame = grabber.grab();
                    if (frame == null) {
                        continue;
                    }


                    // Convert the frame to a BufferedImage
                    BufferedImage bufferedImage = converter.convert(frame);
                    if (bufferedImage != null) {
                        // Update the ImageView with the captured frame on the JavaFX Application Thread

                        BufferedImage scaledImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = scaledImage.createGraphics();

                        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        g2d.drawImage(bufferedImage, 0, 0, WIDTH, HEIGHT, null);
                        g2d.dispose();

                        Platform.runLater(() -> {
                            SwingFXUtils.toFXImage(scaledImage, writableImage);
                        });
                    }

                    // Control frame rate
                    Thread.sleep(1000 / frame_rate);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        captureThread.setDaemon(true);
        captureThread.start();
    }
}
