package pl.javahowtoprogramgui.section_18.t_18_19;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MultipleFractalController {
    private static final int MIN_LEVEL=0;
    private static final int MAX_LEVEL=15;

    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Label levelLabel;
    private Color currentColor= Color.BLUE;
    private int level = MIN_LEVEL;
    private GraphicsContext gc;

    public void initialize(){
        levelLabel.setText("Poziom: "+level);
        colorPicker.setValue(currentColor);
        gc = canvas.getGraphicsContext2D();
        drawFractal();
    }
    @FXML
    private void colorSelected(ActionEvent e) {
        currentColor=colorPicker.getValue();
        drawFractal();
    }

    @FXML
    private void decreasedLevelButtonPressed(ActionEvent e) {
        if(level>MIN_LEVEL){
            --level;
            levelLabel.setText("Poziom: "+level);
            drawFractal();
        }
    }

    @FXML
    private void increasedLevelButtonPressed(ActionEvent e) {
        if(level<MAX_LEVEL){
            ++level;
            levelLabel.setText("Poziom: "+level);
            drawFractal();
        }
    }

    private void drawFractal() {
      gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
      gc.setStroke(currentColor);
      drawFractal(level,200,200,350,80);
      drawFractal(level,200,200,350,350);
      drawFractal(level,200,200,80,80);
      drawFractal(level,200,200,80,350);
    }

    private void drawFractal(int level, int xA, int yA, int xB, int yB) {
        if(level==0){
            gc.strokeLine(xA, yA, xB, yB);
        }else {
            int xC=(xA+xB)/2;
            int yC=(yA+yB)/2;

            int xD=xA+(xC-xA)/2-(yC-yA)/2;
            int yD=yA+(yC-yA)/2+(xC-xA)/2;

            drawFractal(level-1,xD,yD,xA,yA);
            drawFractal(level-1,xD,yD,xC,yC);
            drawFractal(level-1,xD,yD,xB,yB);
        }
    }
}