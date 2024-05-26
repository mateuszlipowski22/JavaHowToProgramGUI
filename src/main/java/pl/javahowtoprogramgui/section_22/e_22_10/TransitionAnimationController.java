package pl.javahowtoprogramgui.section_22.e_22_10;

import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import org.controlsfx.dialog.ExceptionDialog;

import java.net.URL;

public class TransitionAnimationController {

    @FXML
    private Rectangle rectangle;

    @FXML
    private void startButtonPressed(ActionEvent e) {

        FillTransition fillTransition = new FillTransition(Duration.seconds(1));
        fillTransition.setToValue(Color.CYAN);
        fillTransition.setCycleCount(2);
        fillTransition.setAutoReverse(true);

        StrokeTransition strokeTransition = new StrokeTransition(Duration.seconds(1));
        strokeTransition.setToValue(Color.BLUE);
        strokeTransition.setCycleCount(2);
        strokeTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition(
                fillTransition, strokeTransition);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1));
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1));
        rotateTransition.setByAngle(360.0);
        rotateTransition.setCycleCount(2);
        rotateTransition.setInterpolator(Interpolator.EASE_BOTH);
        rotateTransition.setAutoReverse(true);

        Path path = new Path(new MoveTo(45,45), new LineTo(45,0),
            new LineTo(90,0), new LineTo(90,90), new LineTo(0,45));

        PathTransition pathTransition = new PathTransition(Duration.seconds(2), path);
        pathTransition.setCycleCount(2);
        pathTransition.setInterpolator(Interpolator.EASE_IN);
        pathTransition.setAutoReverse(true);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1));
        scaleTransition.setByX(0.75);
        scaleTransition.setByY(0.75);
        scaleTransition.setCycleCount(2);
        pathTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setAutoReverse(true);

        SequentialTransition sequentialTransition = new SequentialTransition(
                rectangle,parallelTransition, fadeTransition, rotateTransition, pathTransition, scaleTransition);
        sequentialTransition.play();
    }
}
