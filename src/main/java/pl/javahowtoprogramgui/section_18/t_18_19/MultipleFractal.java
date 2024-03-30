package pl.javahowtoprogramgui.section_18.t_18_19;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MultipleFractal extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("MultipleFractal.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Multiple Fraktale");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
