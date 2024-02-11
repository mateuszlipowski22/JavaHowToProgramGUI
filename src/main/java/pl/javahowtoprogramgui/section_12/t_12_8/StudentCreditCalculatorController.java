package pl.javahowtoprogramgui.section_12.t_12_8;

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

public class StudentCreditCalculatorController {
    public static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    @FXML
    public Slider loanPeriodSlider;
    @FXML
    public Label loanPeriodLabel;
    @FXML
    public TextField intrestRateTextField;
    @FXML
    public TextField monthlyInterestCustomTextField;
    @FXML
    public TextField creditValueTextField;

    private BigDecimal loadPeriod = new BigDecimal("15");


    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            Double creditValue = Double.valueOf(creditValueTextField.getText());
            Double interestRate = Double.parseDouble(intrestRateTextField.getText()) / 100.0;

            Double y = 1+(interestRate/12.0);
            Double n = loanPeriodSlider.getValue()*12;
            Double yn = Math.pow(y,n);
            Double monthlyInterest = creditValue*yn*(y-1)/(yn-1);
            monthlyInterestCustomTextField.setText(currency.format(monthlyInterest));
        } catch (NumberFormatException exception) {
            creditValueTextField.setText("Wpisz poprawne kwote");
            creditValueTextField.requestFocus();
        }
    }

    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);

        loanPeriodSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                        loadPeriod = BigDecimal.valueOf(newValue.intValue());
                        loanPeriodLabel.setText(String.format("Miesięczna rata dla %s lat kredytowania",loadPeriod.toString()));
                    }
                }
        );
    }
}
