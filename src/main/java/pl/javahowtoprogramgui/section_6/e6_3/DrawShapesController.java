package pl.javahowtoprogramgui.section_6.e6_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.security.SecureRandom;

public class DrawShapesController {
    @FXML
    private Canvas canvas;
    private final SecureRandom secureRandom = new SecureRandom();

    @FXML
    void drawShapesButtonPressed(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        int numberOfCircles = 5;
        for (int i = 1; i <= numberOfCircles; i++) {
            gc.setFill(Color.rgb(secureRandom.nextInt(256), secureRandom.nextInt(256), secureRandom.nextInt(256)));

            double x = secureRandom.nextDouble(canvas.getWidth());
            double y = secureRandom.nextDouble(canvas.getHeight());
            double width = secureRandom.nextDouble(canvas.getHeight()/2);
            double height = secureRandom.nextDouble(canvas.getHeight()/2);

            if(secureRandom.nextBoolean()){
                gc.fillOval(x, y, width, height);
            }else{
                gc.fillRect(x, y, width, height);
            }
        }
    }
}
