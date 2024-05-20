package pl.javahowtoprogramgui.section_22.e_22_4;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BasicShapes extends Application {
   @Override
   public void start(Stage stage) throws Exception {

      Parent root =
              FXMLLoader.load(getClass().getResource("BasicShapes.fxml"));

      Scene scene = new Scene(root);

      scene.getStylesheets().add(getClass().getResource("BasicShapes.css").toExternalForm());

      stage.setTitle("Podstawoe kształty i CSS");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}