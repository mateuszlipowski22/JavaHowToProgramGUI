package pl.javahowtoprogramgui.section_23.e_23_11_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FindPrimes extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FindPrimes.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Liczby pierwsze - sito Eratostensena");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
