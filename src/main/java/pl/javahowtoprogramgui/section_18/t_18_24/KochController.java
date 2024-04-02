package pl.javahowtoprogramgui.section_18.t_18_24;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class KochController {
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
        drawKoch();
    }
    @FXML
    private void colorSelected(ActionEvent e) {
        currentColor=colorPicker.getValue();
        drawKoch();
    }

    @FXML
    private void decreasedLevelButtonPressed(ActionEvent e) {
        if(level>MIN_LEVEL){
            --level;
            levelLabel.setText("Poziom: "+level);
            drawKoch();
        }
    }

    @FXML
    private void increasedLevelButtonPressed(ActionEvent e) {
        if(level<MAX_LEVEL){
            ++level;
            levelLabel.setText("Poziom: "+level);
            drawKoch();
        }
    }

    private void drawKoch() {
      gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
      gc.setStroke(currentColor);
        drawKoch(level,40,100,350,350);
    }

    private void drawKoch(int level, int xA, int yA, int xB, int yB) {
        if(level==0){
            gc.strokeLine(xA, yA, xB, yB);
        }else {
            int xM1=xA+(xB-xA)/3;
            int yM1=yA+(yB-yA)/3;

            int xM2=xA+(xB-xA)*2/3;
            int yM2=yA+(yB-yA)*2/3;

            int xS= (int) (xM1+(xM2-xM1)*Math.cos(1.047)-(yM2-yM1)*Math.sin(1.047));
            int yS= (int) (yM1+(xM2-xM1)*Math.sin(1.047)+(yM2-yM1)*Math.cos(1.047));

            drawKoch(level-1,xA,yA,xM1,yM1);
            drawKoch(level-1,xM1,yM1,xS,yS);
            drawKoch(level-1,xS,yS,xM2,yM2);
            drawKoch(level-1,xM2,yM2,xB,yB);
        }
    }
}