package pl.javahowtoprogramgui.section_6.e6_7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawSmiley extends Application {
   @Override
   public void start(Stage stage) throws Exception {

      Parent root =
              FXMLLoader.load(getClass().getResource("DrawSmiley.fxml"));

      Scene scene = new Scene(root);

      stage.setTitle("Rysuj uśmiech");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args); // Tworzy obiekt DrawLines i wywołuje jego metodę start
   }
}
