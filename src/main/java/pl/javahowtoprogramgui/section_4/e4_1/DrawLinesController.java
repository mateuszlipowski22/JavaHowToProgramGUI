package pl.javahowtoprogramgui.section_4.e4_1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawLinesController { 
   @FXML private Canvas canvas;

   @FXML
   void drawLinesButtonPressed(ActionEvent event) {
      GraphicsContext gc = canvas.getGraphicsContext2D();

      int number = 40;

      for(int i=0; i<=number;i++){
         gc.strokeLine(0, 0,  (canvas.getWidth())*((double) i/number),  (canvas.getHeight()*((double) (number-i)/number)));
      }
   }
}
