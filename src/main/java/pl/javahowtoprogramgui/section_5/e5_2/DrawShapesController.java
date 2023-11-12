package pl.javahowtoprogramgui.section_5.e5_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawShapesController {
    @FXML
    private Canvas canvas;

    @FXML
    void drawOvalsButtonPressed(ActionEvent event) {
        draw();
    }

    @FXML
    void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int step = 10;

        for (int i = 0; i < 10; i++) {
            gc.strokeOval(canvas.getWidth() / 2 - (10 + i * step), canvas.getHeight() / 2 - (10 + i * step)
                    , 2*(10 + i * step), 2*(10 + i * step));
        }
    }
}

