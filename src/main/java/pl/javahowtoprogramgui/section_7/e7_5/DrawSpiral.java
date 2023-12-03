package pl.javahowtoprogramgui.section_7.e7_5;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawSpiral extends Application {
   @Override
   public void start(Stage stage) throws Exception {

      Parent root =
              FXMLLoader.load(getClass().getResource("DrawSpiral.fxml"));

      Scene scene = new Scene(root);

      stage.setTitle("Rysowanie spirali");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
