package pl.javahowtoprogramgui.section_12.t_12_7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MortgageCreditCalculator extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("MortgageCreditCalculator.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Kalkulator raty kredytowej");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
