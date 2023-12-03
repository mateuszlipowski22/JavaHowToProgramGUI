package pl.javahowtoprogramgui.section_8.e8_2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyLine {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private double width;
    private Color strokeColor;

    public MyLine(double x1, double x2, double y1, double y2, double width, Color strokeColor) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.width = width;
        this.strokeColor = strokeColor;
    }

    public void draw(GraphicsContext gc){
        gc.setStroke(strokeColor);
        gc.strokeLine(x1,x2,y1,y2);
        gc.setLineWidth(width/10);
    }
}
