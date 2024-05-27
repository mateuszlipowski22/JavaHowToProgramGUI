package pl.javahowtoprogramgui.section_22.e_22_13;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.security.SecureRandom;

public class TimelineAnimationController {

    @FXML
    private Circle c;

    @FXML
    private Pane pane;

    public void initialize() {
        SecureRandom secureRandom = new SecureRandom();

        Timeline timelineAnimation = new Timeline(
                new KeyFrame(Duration.millis(10),
                        new EventHandler<ActionEvent>() {
                            int dx = 1 + secureRandom.nextInt(5);
                            int dy = 1 + secureRandom.nextInt(5);
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                c.setLayoutX(c.getLayoutX()+dx);
                                c.setLayoutY(c.getLayoutY()+dy);
                                Bounds bounds = pane.getBoundsInLocal();

                                if(hitRightOrLeftEdge(bounds)){
                                    dx*=-1;
                                }

                                if(hitTopOrBottomEdge(bounds)){
                                    dy*=-1;
                                }
                            }
                        })
        );
        timelineAnimation.setCycleCount(Timeline.INDEFINITE);
        timelineAnimation.play();
    }

    private boolean hitRightOrLeftEdge(Bounds bounds){
        return (c.getLayoutX() <= (bounds.getMinX() + c.getRadius())) ||
                (c.getLayoutX() >= (bounds.getMaxX() - c.getRadius()));
    }

    private boolean hitTopOrBottomEdge(Bounds bounds){
        return (c.getLayoutY() <= (bounds.getMinY() + c.getRadius())) ||
                (c.getLayoutY() >= (bounds.getMaxY() - c.getRadius()));
    }
}
