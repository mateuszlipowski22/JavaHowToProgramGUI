package pl.javahowtoprogramgui.section_8.e8_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.security.SecureRandom;

public class DrawLinesController {
    @FXML
    private Canvas canvas;
    private static final SecureRandom secureRandom = new SecureRandom();
    @FXML
    void drawLinesButtonPressed(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        MyLine[] lines = new MyLine[100];

        final int width = (int) canvas.getWidth();
        final int height = (int) canvas.getHeight();

        for(int count = 0; count< lines.length ; count++){
            int x1 = secureRandom.nextInt(width);
            int x2 = secureRandom.nextInt(width);
            int y1 = secureRandom.nextInt(height);
            int y2 = secureRandom.nextInt(height);
            int lineWidth = secureRandom.nextInt(width);

            Color color = Color.rgb(secureRandom.nextInt(256),
                    secureRandom.nextInt(256),
                    secureRandom.nextInt(256));

            lines[count]=new MyLine(x1,x2,y1,y2,lineWidth,color);
        }

        for (MyLine line : lines){
            line.draw(gc);
        }
    }
}
