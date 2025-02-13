package pl.javahowtoprogramgui.section_23.e_23_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FibonacciNumbersController {

    @FXML
    private TextField numberTextField;
    @FXML
    private Button goButton;
    @FXML
    private Label messageLabel;
    @FXML
    private Label fibonacciLabel;
    @FXML
    private Label nthLabel;
    @FXML
    private Label nthFibonacciLabel;

    private long n1=0;
    private long n2=1;
    private int number=1;

    @FXML
    void goButtonPressed(ActionEvent event){
        try{
            int input = Integer.parseInt(numberTextField.getText());

            FibonacciTask task = new FibonacciTask(input);

            messageLabel.textProperty().bind(task.messageProperty());

            task.setOnRunning((succeededEven) -> {
                goButton.setDisable(true);
                fibonacciLabel.setText("");
            });

            task.setOnSucceeded((succeededEven) -> {
                fibonacciLabel.setText(task.getValue().toString());
                goButton.setDisable(false);
            });

            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        }catch (NumberFormatException e){
            numberTextField.setText("Wpisz liczbę całkowitą");
            numberTextField.selectAll();
            numberTextField.requestFocus();
        }
    }
    @FXML
    void nextNumberButtonPressed(ActionEvent event){
        nthLabel.setText("Fibonacci dla "+number+": ");
        nthFibonacciLabel.setText(String.valueOf(n2));
        long temp = n1+n2;
        n1=n2;
        n2=temp;
        ++number;
    }
}
