package pl.javahowtoprogramgui.section_4.e1;// Rysunek 4.27. DrawLinesController.java
// Użycie strokeLine do połączenia rogów kanwy
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawLinesController { 
   @FXML private Canvas canvas; // Służy do pobrania GraphicsContext

   // Gdy użytkownik kliknie przycisk, Rysuj linie, narysuj na kanwie dwie linie
   @FXML
   void drawLinesButtonPressed(ActionEvent event) {
      // Pobierz GraphicsContext, który posłuży do rysowania na Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();

      // Narysuj linię z lewego górnego rogu do prawego dolnego rogu
      gc.strokeLine(0, 0, canvas.getWidth(), canvas.getHeight());

      // Narysuj linię z prawego górnego do lewego dolnego rogu
      gc.strokeLine(canvas.getWidth(), 0, 0, canvas.getHeight());
   }
}

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
