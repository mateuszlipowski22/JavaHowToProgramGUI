package pl.javahowtoprogramgui.section_7.e7_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class DrawRainbowController {
    @FXML
    private Canvas canvas;

    private final Color[] colors = {Color.WHITE, Color.WHITE, Color.VIOLET, Color.INDIGO,
            Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};

    @FXML
    void drawRainbowButtonPressed(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        final int radius = 20;

        final double centerX = canvas.getWidth() / 2;
        final double maxY = canvas.getHeight() - 10;

        for (int counter = colors.length; counter > 0; counter--) {
            gc.setFill(colors[counter - 1]);
            gc.fillArc(centerX - counter * radius,
                    maxY - counter * radius, counter * radius * 2,
                    counter * radius * 2, 0, 180, ArcType.OPEN);
        }
    }
}
