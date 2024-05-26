package pl.javahowtoprogramgui.section_22.e_22_10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TransitionAnimation extends Application{

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("TransitionAnimation.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("TransitionAnimation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
