package pl.javahowtoprogramgui.section_12.t_12_7;

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

public class MortgageCreditCalculatorController {
    public static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    public static final NumberFormat percent = NumberFormat.getPercentInstance();
    @FXML
    public TextField totalPerPersonTextField;
    @FXML
    public TextField priceTextField;
    @FXML
    public Slider loanPeriodSlider;
    @FXML
    public Label loanPeriodLabel;
    @FXML
    public TextField contributionTextField;
    @FXML
    public TextField intrestRateTextField;
    @FXML
    public TextField monthlyInterest20TextField;
    @FXML
    public TextField monthlyInterestCustomTextField;
    @FXML
    public TextField monthlyInterest30TextField;
    @FXML
    public TextField monthlyInterest10TextField;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    private BigDecimal loadPeriod = new BigDecimal("15");
    private BigDecimal price;
    private BigDecimal contribution;

    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            Double price = Double.valueOf(priceTextField.getText());
            Double contribution = Double.valueOf(contributionTextField.getText());
            Double interestRate = Double.parseDouble(intrestRateTextField.getText()) / 100.0;
            Double mortgageValue = price-contribution;

            Double y = 1+(interestRate/12.0);
            Double n = loanPeriodSlider.getValue()*12;
            Double n10 = 10*12d;
            Double n20 = 20*12d;
            Double n30 = 30*12d;
            Double yn = Math.pow(y,n);
            Double yn10 = Math.pow(y,n10);
            Double yn20 = Math.pow(y,n20);
            Double yn30 = Math.pow(y,n30);
            Double monthlyInterest = mortgageValue*yn*(y-1)/(yn-1);
            Double monthlyInterest10 = mortgageValue*yn10*(y-1)/(yn10-1);
            Double monthlyInterest20 = mortgageValue*yn20*(y-1)/(yn20-1);
            Double monthlyInterest30 = mortgageValue*yn30*(y-1)/(yn30-1);
            monthlyInterestCustomTextField.setText(currency.format(monthlyInterest));
            monthlyInterest10TextField.setText(currency.format(monthlyInterest10));
            monthlyInterest20TextField.setText(currency.format(monthlyInterest20));
            monthlyInterest30TextField.setText(currency.format(monthlyInterest30));
        } catch (NumberFormatException exception) {
            priceTextField.setText("Wpisz poprawne kwoty");
            priceTextField.requestFocus();
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
