package pl.javahowtoprogramgui.section_24.e_24_9;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;


public class AddressBookController {
    @FXML
    private ListView<Person> listView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField findByLastNameTextField;

    private final PersonQueries personQueries = new PersonQueries();

    private final ObservableList<Person> contactList = FXCollections.observableArrayList();

    public AddressBookController() throws SQLException {
    }

    public void initialize() {
        listView.setItems(contactList);
        getAllEntries();
        displayContact(listView.getSelectionModel().getSelectedItem());

        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                    displayContact(newValue);
                }
        );
    }

    private void getAllEntries() {
        contactList.setAll(personQueries.getAllPeople());
        selectFirstEntry();
    }

    private void selectFirstEntry() {
        listView.getSelectionModel().selectFirst();
    }

    private void displayContact(Person person) {
        if (person != null) {
            firstNameTextField.setText(person.getFirstName());
            lastNameTextField.setText(person.getLastName());
            emailTextField.setText(person.getEmail());
            phoneTextField.setText(person.getPhoneNumber());
        } else {
            firstNameTextField.clear();
            lastNameTextField.clear();
            emailTextField.clear();
            phoneTextField.clear();
        }
    }

    @FXML
    void addEntryButtonPressed(ActionEvent event) {
        int result = personQueries.addPerson(
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                emailTextField.getText(),
                phoneTextField.getText()
        );
        if (result == 1) {
            displayAlert(Alert.AlertType.INFORMATION, "Dodano wpis", "Pomyślne dodanie nowego wpisu");
        } else {
            displayAlert(Alert.AlertType.ERROR, "Wpisu nie dodano", "Nie udało się dodać wpisu");
        }
    }

    @FXML
    void findButtonPressed(ActionEvent event) {
        List<Person> people = personQueries.getPeopleByLastName(findByLastNameTextField.getText() + "%");

        if (people.size() > 0) {
            contactList.setAll(people);
            selectFirstEntry();
        } else {
            displayAlert(Alert.AlertType.INFORMATION, "Nazwiska nie znaleziono", "Nie ma żadnych wpisów dotyczących podanego nazwiska");
        }
    }

    @FXML
    public void browseAllButtonPressed(ActionEvent event) {
        getAllEntries();
    }

    private void displayAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
