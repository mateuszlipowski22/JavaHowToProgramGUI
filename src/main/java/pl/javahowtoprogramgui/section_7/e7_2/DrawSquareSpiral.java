package pl.javahowtoprogramgui.section_7.e7_2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DrawSquareSpiral extends Application {
   @Override
   public void start(Stage stage) throws Exception {

      Parent root =
              FXMLLoader.load(getClass().getResource("DrawSquareSpiral.fxml"));

      Scene scene = new Scene(root);

      stage.setTitle("Rysowanie kwadratowej spirali");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
