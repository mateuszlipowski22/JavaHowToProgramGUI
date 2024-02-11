package pl.javahowtoprogramgui.section_12.t_12_10;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class PetrolConsumptionCalculatorController {

    @FXML
    public TextField mileageTextField;
    @FXML
    public TextField fuelConsumptionTextField;
    @FXML
    public TextField distancePerLiterTextField;


    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            Double mileage = Double.valueOf(mileageTextField.getText());
            Double fuelConsumption = Double.parseDouble(fuelConsumptionTextField.getText());

            distancePerLiterTextField.setText(String.format("%.2f",mileage/fuelConsumption));
        } catch (NumberFormatException exception) {
            mileageTextField.setText("Wpisz poprawne wartości");
            mileageTextField.requestFocus();
        }
    }
}
