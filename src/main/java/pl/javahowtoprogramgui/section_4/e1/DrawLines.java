package pl.javahowtoprogramgui.section_4.e1;// Rysunek 4.28. DrawLines.java
// Główna klasa aplikacji, która wczytuje i wyświetla interfejs użytkownika
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawLines extends Application {
   @Override
   public void start(Stage stage) throws Exception {

      Parent root =
              FXMLLoader.load(getClass().getResource("DrawLines.fxml"));

      Scene scene = new Scene(root); // Dołącz graf scenki do scenki


      stage.setTitle("Rysowanie linii"); // Wyświetlany na pasku tytułu
      stage.setScene(scene); // Dołącz scenkę do sceny
      stage.show(); // Wyświetl scenę
   }

   public static void main(String[] args) {
      launch(args); // Tworzy obiekt DrawLines i wywołuje jego metodę start
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
