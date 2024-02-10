package pl.javahowtoprogramgui.section_12.t_12_6;

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

public class AdvanceTipCalculatorController {

    public static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    public static final NumberFormat percent = NumberFormat.getPercentInstance();
    @FXML
    public TextField numberOfPeopleTextField;
    @FXML
    public TextField totalPerPersonTextField;

    private BigDecimal tipPercentage = new BigDecimal("0.15");

    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal numberOfPeople = new BigDecimal(numberOfPeopleTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);
            BigDecimal totalPerPerson = total.divide(numberOfPeople, RoundingMode.HALF_UP);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
            totalPerPersonTextField.setText(currency.format(totalPerPerson));
        } catch (NumberFormatException exception) {
            amountTextField.setText("Wpisz kwote");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }

    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);
        numberOfPeopleTextField.setText("1");

        tipPercentageSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                        tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
                        tipPercentageLabel.setText(percent.format(tipPercentage));
                    }
                }
        );
    }
}
