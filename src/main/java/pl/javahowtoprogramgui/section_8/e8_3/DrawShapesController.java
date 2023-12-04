package pl.javahowtoprogramgui.section_8.e8_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.security.SecureRandom;

public class DrawShapesController {
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

    @FXML
    void drawOvalsButtonPressed(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        MyOval[] ovals = new MyOval[100];

        final int width = (int) canvas.getWidth();
        final int height = (int) canvas.getHeight();

        for (int count = 0; count < ovals.length; count++) {
            int x1 = secureRandom.nextInt(width);
            int x2 = secureRandom.nextInt(width);
            int y1 = secureRandom.nextInt(height);
            int y2 = secureRandom.nextInt(height);

            Color borderColor = Color.rgb(secureRandom.nextInt(256),
                    secureRandom.nextInt(256),
                    secureRandom.nextInt(256));

            Color strokeColor = Color.rgb(secureRandom.nextInt(256),
                    secureRandom.nextInt(256),
                    secureRandom.nextInt(256));

            boolean isFilled = secureRandom.nextBoolean();
            ovals[count] = new MyOval(x1, x2, y1, y2, borderColor, strokeColor, isFilled);
        }

        for (MyOval oval : ovals) {
            oval.draw(gc);
        }
    }

    @FXML
    void drawRectanglesButtonPressed(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        MyRectangle[] rectangles = new MyRectangle[100];

        final int width = (int) canvas.getWidth();
        final int height = (int) canvas.getHeight();

        for(int count = 0; count< rectangles.length ; count++){
            int x1 = secureRandom.nextInt(width);
            int x2 = secureRandom.nextInt(width);
            int y1 = secureRandom.nextInt(height);
            int y2 = secureRandom.nextInt(height);

            Color borderColor = Color.rgb(secureRandom.nextInt(256),
                    secureRandom.nextInt(256),
                    secureRandom.nextInt(256));

            Color strokeColor = Color.rgb(secureRandom.nextInt(256),
                    secureRandom.nextInt(256),
                    secureRandom.nextInt(256));

            boolean isFilled = secureRandom.nextBoolean();
            rectangles[count]=new MyRectangle(x1,x2,y1,y2,borderColor,strokeColor,isFilled);
        }

        for (MyRectangle rectangle : rectangles){
            rectangle.draw(gc);
        }
    }
}
