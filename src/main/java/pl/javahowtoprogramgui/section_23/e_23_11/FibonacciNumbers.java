package pl.javahowtoprogramgui.section_23.e_23_11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FibonacciNumbers extends Application{

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("FibonacciNumbers.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Liczby Fibonacciego");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
