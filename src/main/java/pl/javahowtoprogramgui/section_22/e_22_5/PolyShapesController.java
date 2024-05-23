package pl.javahowtoprogramgui.section_22.e_22_5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

public class PolyShapesController {

    public Pane drawingAreaPane;

    private enum ShapeType {POLYLINE, POLYGON, PATH}

    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton polylineRadioButton;
    @FXML
    private RadioButton polygonRadioButton;
    @FXML
    private RadioButton pathRadioButton;
    @FXML
    private Polyline polyline; //= new Polyline();
    @FXML
    private Polygon polygon;// = new Polygon();
    @FXML
    private Path path;// = new Path();

    private ShapeType shapeType = ShapeType.POLYLINE;
    private boolean sweepFlag = true;
    public void initialize() {
        polylineRadioButton.setUserData(shapeType.POLYLINE);
        polygonRadioButton.setUserData(shapeType.POLYGON);
        pathRadioButton.setUserData(shapeType.PATH);
        displayShape();
    }

    public void shapeRadioButtonSelected(ActionEvent actionEvent) {
        shapeType = (ShapeType) toggleGroup.getSelectedToggle().getUserData();
        displayShape();
    }

    private void displayShape() {
        polygon.setVisible(shapeType==ShapeType.POLYGON);
        polyline.setVisible(shapeType==ShapeType.POLYLINE);
        path.setVisible(shapeType==ShapeType.PATH);
    }


    public void clearButtonPressed(ActionEvent actionEvent) {
        polyline.getPoints().clear();
        polygon.getPoints().clear();
        path.getElements().clear();
    }

    public void drawingAreaMouseClicked(MouseEvent mouseEvent) {

        polyline.getPoints().addAll(mouseEvent.getX(),mouseEvent.getY());
        polygon.getPoints().addAll(mouseEvent.getX(),mouseEvent.getY());

        if(path.getElements().isEmpty()){
            path.getElements().add(new MoveTo(mouseEvent.getX(),mouseEvent.getY()));
            path.getElements().add(new ClosePath());
        }else {
            ArcTo arcTo = new ArcTo();
            arcTo.setX(mouseEvent.getX());
            arcTo.setY(mouseEvent.getY());
            arcTo.setRadiusX(100.0);
            arcTo.setRadiusY(100.0);
            arcTo.setSweepFlag(sweepFlag);
            sweepFlag=!sweepFlag;
            path.getElements().add(path.getElements().size()-1,arcTo);
        }

    }
}
