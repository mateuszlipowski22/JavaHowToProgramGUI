package pl.javahowtoprogramgui.section_23.e_23_11_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FindPrimesController {

    @FXML
    private TextField inputTextField;
    @FXML
    private Button getPrimesButton;
    @FXML
    private ListView<Integer> primesListView;
    @FXML
    private Button cancelButton;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label statusLabel;

    private ObservableList<Integer> primes = FXCollections.observableArrayList();

    private PrimeCalculatorTask task;

    public void initialize(){
        primesListView.setItems(primes);
    }

    @FXML
    void getPrimesButtonPressed(ActionEvent event) {
        primes.clear();
        try {
            int input = Integer.parseInt(inputTextField.getText());

            task = new PrimeCalculatorTask(input);

            statusLabel.textProperty().bind(task.messageProperty());

            progressBar.progressProperty().bind(task.progressProperty());

            task.valueProperty().addListener(
                    (observable, oldValue, newValue) ->{
                        if(newValue != 0){
                            primes.add(newValue);
                            primesListView.scrollTo(primesListView.getItems().size());
                        }
                    }
            );


            task.setOnRunning((succeededEven) -> {
                getPrimesButton.setDisable(true);
                cancelButton.setDisable(false);
            });

            task.setOnSucceeded((succeededEven) -> {
                getPrimesButton.setDisable(false);
                cancelButton.setDisable(true);
            });

            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        } catch (NumberFormatException e) {
            inputTextField.setText("Wpisz liczbę całkowitą");
            inputTextField.selectAll();
            inputTextField.requestFocus();
        }
    }

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        if(task!=null){
            task.cancel();
            getPrimesButton.setDisable(false);
            cancelButton.setDisable(true);
        }
    }
}
