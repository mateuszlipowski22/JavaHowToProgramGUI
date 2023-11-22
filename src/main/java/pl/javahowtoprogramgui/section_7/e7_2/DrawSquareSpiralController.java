package pl.javahowtoprogramgui.section_7.e7_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class DrawSquareSpiralController {
    @FXML
    private Canvas canvas;

    private final Color[] colors = {Color.WHITE, Color.WHITE, Color.VIOLET, Color.INDIGO,
            Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};

    @FXML
    void drawSquareSpiralButtonPressed(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double initialLength = canvas.getHeight() / 20;
        double length = initialLength;

        int maxSize = 40;

        double startX = canvas.getWidth() / 2;
        double startY = canvas.getHeight() / 2;
        double endX;
        double endY;
        for (int counter = 1; counter < maxSize; counter++) {

            if (counter % 2 == 0) {
                endX = startX + Math.pow(-1, counter/2) * length;
                endY = startY;
            } else {
                endX = startX;
                endY = startY - Math.pow(-1, counter/2 + 1) * length;
            }

            gc.strokeLine(startX, startY, endX, endY);

            length = length + ((counter+1) % 2) * initialLength;
            startX = endX;
            startY = endY;
        }
    }
}
