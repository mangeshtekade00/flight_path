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
    private final double[][] flight1 = {{0, 0}, {1, 3}, {3, 2}, {2, 1}};
    private final double[][] flight2 = {{0, 0}, {1, 1}, {2, 2}};
    private final double[][] flight3 = {{0, 0}, {3, 1}, {4, 3}, {2, 3}};

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Scale factor to better visualize the points
        double scaleFactor = 100;

        // Draw flight paths
        drawFlightPath(gc, flight1, Color.BLUE, scaleFactor, new int[]{1, 3}); // Circular points at (1,3) and (2,1)
        drawFlightPath(gc, flight2, Color.YELLOW, scaleFactor, new int[]{1, 2}); // Circular points at (1,1) and (2,2)
        drawFlightPath(gc, flight3, Color.RED, scaleFactor, new int[]{1, 3}); // Circular points at (3,1) and (2,3)

        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Flight Paths");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawFlightPath(GraphicsContext gc, double[][] flight, Color color, double scaleFactor, int[] circularPoints) {
        gc.setStroke(color);
        gc.setLineWidth(2);
        gc.setFill(color);

        for (int i = 0; i < flight.length - 1; i++) {
            double x1 = flight[i][0] * scaleFactor;
            double y1 = flight[i][1] * scaleFactor;
            double x2 = flight[i + 1][0] * scaleFactor;
            double y2 = flight[i + 1][1] * scaleFactor;

            gc.strokeLine(x1, y1, x2, y2);

            // Draw circular points if they match the index
            if (contains(circularPoints, i + 1)) {
                gc.setFill(Color.YELLOW); // Color for circular points
                gc.fillOval(x1 - 5, y1 - 5, 10, 10); // Draw circular point
                gc.setFill(color); // Reset color for path lines
            }
            gc.fillOval(x1 - 3, y1 - 3, 6, 6); // Draw points
            gc.fillText("(" + flight[i][0] + "," + flight[i][1] + ")", x1 + 5, y1 - 5);
        }
        // Draw text for the last point
        double xLast = flight[flight.length - 1][0] * scaleFactor;
        double yLast = flight[flight.length - 1][1] * scaleFactor;
        gc.fillOval(xLast - 3, yLast - 3, 6, 6); // Draw the last point
        gc.fillText("(" + flight[flight.length - 1][0] + "," + flight[flight.length - 1][1] + ")", xLast + 5, yLast - 5);
    }

    private boolean contains(int[] array, int value) {
        for (int elem : array) {
            if (elem == value) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
