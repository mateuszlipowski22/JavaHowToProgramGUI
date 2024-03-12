package pl.javahowtoprogramgui.section_15.e_15_6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileChooserTestController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button selectFileButton;
    @FXML
    private Button selectDirectoryButton;
    @FXML
    private TextArea textArea;

    @FXML
    private void selectFileButtonPressed(ActionEvent e) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");

        fileChooser.setInitialDirectory(new File("."));

        File file = fileChooser.showOpenDialog(borderPane.getScene().getWindow());

        if (file != null) {
            analyzePath(file.toPath());
        } else {
            textArea.setText("Wybierz plik lub folder");
        }
    }

    @FXML
    private void selectDirectoryButtonPressed(ActionEvent e) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wybierz folder");

        directoryChooser.setInitialDirectory(new File("."));

        File file = directoryChooser.showDialog(borderPane.getScene().getWindow());

        if (file != null) {
            analyzePath(file.toPath());
        } else {
            textArea.setText("Wybierz plik lub folder");
        }
    }

    private void analyzePath(Path path) {
        try {
            if (path != null && Files.exists(path)) {
                StringBuilder builder = new StringBuilder();

                builder.append(String.format("%n%s istnieje%n", path.getFileName()));
                builder.append(String.format("%s folderem%n", Files.isDirectory(path) ? "Jest" : "Nie jest"));
                builder.append(String.format("%s ścieżką bezwzględną%n", path.isAbsolute() ? "Jest" : "Nie jest"));
                builder.append(String.format("Ostatnia modyfikacja: %s%n", Files.getLastModifiedTime(path)));
                builder.append(String.format("Rozmiar: %s%n", Files.size(path)));
                builder.append(String.format("Ścieżka: %s%n", path));
                builder.append(String.format("Ścieżka bezwzględna: %s%n", path.toAbsolutePath()));

                if (Files.isDirectory(path)) {
                    builder.append(String.format("Zawartość folderu:%n"));
                    Files.newDirectoryStream(path).forEach(p -> builder.append(String.format("%s%n", p)));
                }
                textArea.setText(builder.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}