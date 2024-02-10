package pl.javahowtoprogramgui.section_12.t_12_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddingCalculatorController {

    @FXML
    private Label resultLabel;

    @FXML
    private TextField firstNumberTextField;

    @FXML
    private TextField secondNumberTextField;

    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            int firstNumber = Integer.parseInt(firstNumberTextField.getText());
            int secondNumber = Integer.parseInt(secondNumberTextField.getText());
            int result = firstNumber+secondNumber;

            resultLabel.setText(String.valueOf(result));
        } catch (NumberFormatException exception) {
            resultLabel.setText("Wpisz poprawną liczbę");
            firstNumberTextField.selectAll();
            firstNumberTextField.requestFocus();
        }
    }

    public void initialize() {
        resultLabel.setText("Uzupełnij liczby");
    }
}
