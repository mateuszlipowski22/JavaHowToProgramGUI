package pl.javahowtoprogramgui.section_22.e_22_13;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TimelineAnimation extends Application{

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("TimelineAnimation.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("TimelineAnimation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
