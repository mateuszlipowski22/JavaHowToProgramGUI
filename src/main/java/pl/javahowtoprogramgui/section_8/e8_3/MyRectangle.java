package pl.javahowtoprogramgui.section_8.e8_3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyRectangle {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Color borderColor;
    private Color strokeColor;
    private boolean isFilled;

    public MyRectangle(double x1, double x2, double y1, double y2, Color borderColor, Color strokeColor, boolean isFilled) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.borderColor = borderColor;
        this.strokeColor = strokeColor;
        this.isFilled = isFilled;
    }

    public MyRectangle() {
        this.x1 = 0;
        this.x2 = 0;
        this.y1 = 0;
        this.y2 = 0;
        this.borderColor = Color.BLACK;
        this.strokeColor = Color.BLACK;
        this.isFilled = true;
    }

    public void draw(GraphicsContext gc){
        gc.setStroke(strokeColor);
        if(isFilled){
            gc.setFill(strokeColor);
            gc.fillRect(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        }else {
            gc.strokeRect(getUpperLeftX(),getUpperLeftY(),getWidth(),getHeight());
        }
    }

    public double getUpperLeftX(){
        return Math.min(x1, x2);
    }

    public double getUpperLeftY(){
        return Math.min(y1, y2);
    }

    public double getWidth(){
        return Math.abs(x1-x2);
    }

    public double getHeight(){
        return Math.abs(y1-y2);
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = (x1>0) ? x1 : 0;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = (x2>0) ? x2 : 0;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = (y1>0) ? y1 : 0;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = (y2>0) ? y2 : 0;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }
}
