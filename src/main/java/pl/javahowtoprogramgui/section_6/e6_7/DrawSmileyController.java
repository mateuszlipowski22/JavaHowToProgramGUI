package pl.javahowtoprogramgui.section_6.e6_7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Thread.currentThread;

public class DrawSmileyController {
    @FXML
    private Canvas canvas;

    private Boolean isClosed;

    @FXML
    public void initialize() {
        drawSmileyButtonPressed();
        this.isClosed=false;
    }

    void drawSmileyButtonPressed() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        gc.setFill(Color.YELLOW);
        gc.fillOval(10, 10, 280, 280);
        gc.strokeOval(10, 10, 280, 280);

        gc.setFill(Color.BLACK);
        gc.fillOval(75, 85, 40, 40);
        gc.fillOval(185, 85, 40, 40);
        gc.fillOval(50, 130, 200, 120);

        gc.setFill(Color.BROWN);
        gc.fillOval(90, 100, 10, 10);
        gc.fillOval(200, 100, 10, 10);

        gc.setFill(Color.PINK);
        gc.fillOval(130, 200, 40, 70);

        gc.setFill(Color.WHITE);
        gc.fillRect(70, 130, 19, 100);
        gc.fillRect(210, 130, 19, 100);
        gc.fillRect(90, 130, 19, 110);
        gc.fillRect(190, 130, 19, 110);
        gc.fillRect(110, 130, 19, 120);
        gc.fillRect(170, 130, 19, 120);

        gc.setFill(Color.YELLOW);
        gc.fillRect(50, 130, 200, 60);
        gc.fillOval(50, 140, 200, 90);
    }

    @FXML
    void drawBlinkButtonPressed(ActionEvent event) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        if(!this.isClosed){
            gc.setFill(Color.YELLOW);
            gc.fillOval(70, 80, 50, 50);
            this.isClosed=true;
        }else {
            gc.setFill(Color.BLACK);
            gc.fillOval(75, 85, 40, 40);
            gc.setFill(Color.BROWN);
            gc.fillOval(90, 100, 10, 10);
            this.isClosed=false;
        }
    }
}
