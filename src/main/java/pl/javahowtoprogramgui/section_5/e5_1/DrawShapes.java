package pl.javahowtoprogramgui.section_5.e5_1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawShapes extends Application {
   @Override
   public void start(Stage stage) throws Exception {

      Parent root =
              FXMLLoader.load(getClass().getResource("DrawShapes.fxml"));

      Scene scene = new Scene(root); // Dołącz graf scenki do scenki

      stage.setTitle("Rysowanie linii"); // Wyświetlany na pasku tytułu
      stage.setScene(scene); // Dołącz scenkę do sceny
      stage.show(); // Wyświetl scenę
   }

   public static void main(String[] args) {
      launch(args); // Tworzy obiekt DrawLines i wywołuje jego metodę start
   }
}
