package pl.javahowtoprogramgui.section_22.e_22_7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Transform;

import java.security.SecureRandom;

public class DrawStarsController {

    @FXML
    private Pane pane;

    private static final SecureRandom random = new SecureRandom();

    public void initialize() {
        Double[] points = {205.0, 150.0, 217.0, 186.0, 259.0, 186.0, 223.0, 204.0, 233.0, 246.0, 205.0, 222.0,
                177.0, 246.0, 187.0, 204.0, 151.0, 186.0, 193.0, 186.0};

        for (int count = 0; count < 18; ++count) {

            Polygon newStar = new Polygon();
            newStar.getPoints().addAll(points);

            newStar.setStroke(Color.GRAY);
            newStar.setFill(Color.rgb(
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextDouble()
            ));

            newStar.getTransforms().add(
                    Transform.rotate(count*20,150,150));
            pane.getChildren().add(newStar);
        }
    }
}
