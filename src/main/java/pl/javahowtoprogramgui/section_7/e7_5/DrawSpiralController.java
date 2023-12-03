package pl.javahowtoprogramgui.section_7.e7_5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class DrawSpiralController {
    @FXML
    private Canvas canvas;

    private final Color[] colors = {Color.WHITE, Color.WHITE, Color.VIOLET, Color.INDIGO,
            Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};

    @FXML
    void drawSpiralButtonPressed(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        final double radius = canvas.getWidth() / 30;
        final int size = 20;

        final double centerX = canvas.getWidth() / 2;
        final double centerY = canvas.getHeight() /2;
        Color color = Color.BROWN;
        gc.setFill(color);
        for (int counter = 0; counter < size; counter++) {
            gc.setFill(color);
            gc.fillArc(
                    centerX - counter * radius-((counter%2!=0)?radius:0),
                    centerY - counter * radius,
                    radius+counter*2*radius ,
                    radius+counter*2*radius,
                    0,
                    (counter%2==0)? 180 : -180,
                    ArcType.CHORD);
            color=color.brighter();

        }
    }
}
