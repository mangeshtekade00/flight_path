package com.example.path_of_all_flight;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class path_of_all_flight extends Application {
    // Define the coordinates for each flight
    private final double[][] flight1 = {{1, 1}, {2, 2}, {3, 3}};
    private final double[][] flight2 = {{1, 1}, {2, 4}, {3, 2}};
    private final double[][] flight3 = {{1, 1}, {4, 2}, {3, 4}};

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Scale factor to better visualize the points
        double scaleFactor = 100;

        // Draw flight paths
        drawFlightPath(gc, flight1, Color.RED, scaleFactor);
        drawFlightPath(gc, flight2, Color.GREEN, scaleFactor);
        drawFlightPath(gc, flight3, Color.BLUE, scaleFactor);

        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Flight Paths");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawFlightPath(GraphicsContext gc, double[][] flight, Color color, double scaleFactor) {
        gc.setStroke(color);
        gc.setLineWidth(2);

        for (int i = 0; i < flight.length - 1; i++) {
            double x1 = flight[i][0] * scaleFactor;
            double y1 = flight[i][1] * scaleFactor;
            double x2 = flight[i + 1][0] * scaleFactor;
            double y2 = flight[i + 1][1] * scaleFactor;

            gc.strokeLine(x1, y1, x2, y2);
            gc.fillOval(x1 - 3, y1 - 3, 6, 6); // Draw points
            gc.fillOval(x2 - 3, y2 - 3, 6, 6); // Draw points

            gc.fillText("(" + flight[i][0] + "," + flight[i][1] + ")", x1 + 5, y1 - 5);
        }
        // Draw text for the last point
        double xLast = flight[flight.length - 1][0] * scaleFactor;
        double yLast = flight[flight.length - 1][1] * scaleFactor;
        gc.fillOval(xLast - 3, yLast - 3, 6, 6); // Draw the last point
        gc.fillText("(" + flight[flight.length - 1][0] + "," + flight[flight.length - 1][1] + ")", xLast + 5, yLast - 5);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
