package pl.javahowtoprogramgui.section_12.t_12_8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentCreditCalculator extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("StudentCreditCalculator.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Kalkulator raty za kredyt studencki");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
