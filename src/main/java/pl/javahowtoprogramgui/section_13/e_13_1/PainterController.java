package pl.javahowtoprogramgui.section_13.e_13_1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PainterController{

    public Pane drawingAreaPane;

    private enum PenSize{
        SMALL(2),
        MEDIUM(4),
        LARGE(6);

        private final int radius;

        PenSize(int radius) {
            this.radius = radius;
        }

        public int getRadius() {
            return radius;
        }
    }

    public RadioButton blackRadioButton;
    public ToggleGroup colorToggleGroup;
    public RadioButton redRadioButton;
    public RadioButton greenRadioButton;
    public RadioButton blueRadioButton;
    public RadioButton smallRadioButton;
    public ToggleGroup sizeToggleGroup;
    public RadioButton mediumRadioButton;
    public RadioButton largeRadioButton;

    private PenSize radius = PenSize.MEDIUM;
    private Paint brushColor = Color.BLACK;

    public void initialize(){
        blackRadioButton.setUserData(Color.BLACK);
        redRadioButton.setUserData(Color.RED);
        blueRadioButton.setUserData(Color.BLUE);
        greenRadioButton.setUserData(Color.GREEN);
        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);
    }

    public void colorRadioButtonSelected(ActionEvent actionEvent) {
        brushColor = (Color) colorToggleGroup.getSelectedToggle().getUserData();
    }

    public void sizeRadioButtonSelected(ActionEvent actionEvent) {
        radius = (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();

    }

    public void undoButtonPressed(ActionEvent actionEvent) {
        int count = drawingAreaPane.getChildren().size();
        if(count>0){
            drawingAreaPane.getChildren().remove(count-1);
        }
    }

    public void clearButtonPressed(ActionEvent actionEvent) {
        drawingAreaPane.getChildren().clear();
    }

    public void drawingAreaMouseDragged(MouseEvent mouseEvent) {
        Circle newCircle = new Circle(mouseEvent.getX(),mouseEvent.getY(),radius.getRadius(),brushColor);
        drawingAreaPane.getChildren().add(newCircle);
    }
}
