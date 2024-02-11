package pl.javahowtoprogramgui.section_12.t_12_9;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarCreditCalculatorController {
    public static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    public static final NumberFormat percent = NumberFormat.getPercentInstance();

    @FXML
    public TextField contributionTextField;
    @FXML
    public TextField intrestRateTextField;
    @FXML
    public TextField monthlyInterest2TextField;
    @FXML
    public TextField monthlyInterest3TextField;
    @FXML
    public TextField monthlyInterest4TextField;
    @FXML
    public TextField monthlyInterest5TextField;
    @FXML
    public TextField carPriceTextField;

    public List<TextField> textFields;
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            Double carPrice = Double.valueOf(carPriceTextField.getText());
            Double contribution = Double.valueOf(contributionTextField.getText());
            Double interestRate = Double.parseDouble(intrestRateTextField.getText()) / 100.0;
            Double creditValue = carPrice-contribution;

            for(int i = 2;i<=5 ;i++){
                textFields.get(i-2).setText(currency.format(calculateMonthlyInterest(interestRate,i,creditValue)));
            }
        } catch (NumberFormatException exception) {
            carPriceTextField.setText("Wpisz poprawne kwoty");
            carPriceTextField.requestFocus();
        }
    }

    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);
        textFields=new ArrayList<>(
                Arrays.asList(
                        monthlyInterest2TextField,
                        monthlyInterest3TextField,
                        monthlyInterest4TextField,
                        monthlyInterest5TextField
                ));
    }

    public Double calculateMonthlyInterest(double interestRate, int numberOfYears, double creditValue){
        double y = 1+(interestRate/12.0);
        double n = numberOfYears*12.0;
        double yn = Math.pow(y,n);
        return creditValue*yn*(y-1)/(yn-1);
    }
}
