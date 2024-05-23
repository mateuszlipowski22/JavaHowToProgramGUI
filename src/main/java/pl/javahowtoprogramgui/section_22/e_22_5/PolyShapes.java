package pl.javahowtoprogramgui.section_22.e_22_5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PolyShapes extends Application{

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("PolyShapes.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("PolyShape");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
