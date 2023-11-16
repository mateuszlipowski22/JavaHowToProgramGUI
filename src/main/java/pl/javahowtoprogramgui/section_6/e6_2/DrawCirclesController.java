package pl.javahowtoprogramgui.section_6.e6_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.security.SecureRandom;

public class DrawCirclesController {
    @FXML
    private Canvas canvas;
    private final SecureRandom secureRandom = new SecureRandom();

    @FXML
    void drawCirclesButtonPressed(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Color colorOne = Color.rgb(secureRandom.nextInt(256), secureRandom.nextInt(256), secureRandom.nextInt(256));
        Color colorTwo = Color.rgb(secureRandom.nextInt(256), secureRandom.nextInt(256), secureRandom.nextInt(256));

        int numberOfCircles = 5;
        double width = canvas.getWidth()/(2*numberOfCircles+1);
        double height = canvas.getHeight()/(2*numberOfCircles+1);
        for (int i = 1; i <= numberOfCircles; i++) {
            gc.setFill((i % 2 == 0) ? colorOne : colorTwo);
            gc.fillOval(width+width*(i-1), height+height*(i-1), canvas.getWidth()-(width*2*i), canvas.getHeight()-(height*2*i));
        }
    }
}
