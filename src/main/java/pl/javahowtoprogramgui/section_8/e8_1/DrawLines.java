package pl.javahowtoprogramgui.section_8.e8_1;
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

      Scene scene = new Scene(root);

      stage.setTitle("Rysowanie lini");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
