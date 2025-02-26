package pl.javahowtoprogramgui.section_24.e_24_7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.embed.swing.SwingNode;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;


public class DisplayQueryResultsController {
    @FXML private BorderPane borderPane;
    @FXML private TextArea queryTextArea;
    @FXML private TextField filterTextField;


//    private static final String DATABASE_URL = "jdbc:derby:books";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/result";
//    private static final String USERNAME = "deitel";
    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "deitel";
    private static final String PASSWORD = "postgres";

//    private static final String DEFAULT_QUERY = "SELECT * FROM authors";
    private static final String DEFAULT_QUERY = "select * from result";

    private ResultSetTableModel tableModel;
    private TableRowSorter<TableModel> sorter;

    public void initialize(){
        queryTextArea.setText(DEFAULT_QUERY);

        try{
            tableModel = new ResultSetTableModel(DATABASE_URL,USERNAME,PASSWORD,DEFAULT_QUERY);
            JTable resultTable = new JTable(tableModel);

            sorter = new TableRowSorter<TableModel>(tableModel);
            resultTable.setRowSorter(sorter);

            SwingNode swingNode = new SwingNode();
            swingNode.setContent(new JScrollPane(resultTable));
            borderPane.setCenter(swingNode);

        } catch (SQLException e) {
            displayAlert(Alert.AlertType.ERROR, "Błąd bazy danych",e.getMessage());
            tableModel.disconnectFromDatabase();
            System.exit(1);
        }
    }

    @FXML
    void submitQueryButtonPressed(ActionEvent event){
        try{
            tableModel.setQuery(queryTextArea.getText());
        }catch (SQLException e) {
            displayAlert(Alert.AlertType.ERROR, "Błąd bazy danych",e.getMessage());

            try {
                tableModel.setQuery(DEFAULT_QUERY);
                queryTextArea.setText(DEFAULT_QUERY);
            } catch (SQLException ex) {
                displayAlert(Alert.AlertType.ERROR, "Błąd bazy danych",ex.getMessage());
                tableModel.disconnectFromDatabase();
                System.exit(1);
            }
        }
    }

    @FXML
    void applyFilterButtonPressed(ActionEvent event){
        String text = filterTextField.getText();

        if(text.isEmpty()){
            sorter.setRowFilter(null);
        }else {
            try{
                sorter.setRowFilter(RowFilter.regexFilter(text));
            }catch (PatternSyntaxException e){
                displayAlert(Alert.AlertType.ERROR, "Błąd wyrażenia regularnego", "Niepoprawny wzorzec");
            }
        }
    }

    private void displayAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
