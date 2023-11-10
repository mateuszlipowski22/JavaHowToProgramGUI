package pl.javahowtoprogramgui.section_4.e4_4;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawLinesController { 
   @FXML private Canvas canvas;

   @FXML
   void drawLinesButtonPressed(ActionEvent event) {
      GraphicsContext gc = canvas.getGraphicsContext2D();

      int number = 50;

      for(int i=0; i<=number;i++){
         gc.strokeLine(0, (canvas.getHeight())*((double) i/number),  (canvas.getWidth())*((double) i/number),  canvas.getHeight());
         gc.strokeLine((canvas.getWidth())*((double) i/number), canvas.getHeight(),  canvas.getWidth(),  canvas.getHeight()*((double) (number-i)/number));
         gc.strokeLine(canvas.getWidth(), canvas.getHeight()*((double) (number-i)/number),  (canvas.getWidth())*((double) (number-i)/number),  0);
         gc.strokeLine((canvas.getWidth())*((double) (number-i)/number), 0,  0,  canvas.getHeight()*((double) (i)/number));
    }
   }
}
