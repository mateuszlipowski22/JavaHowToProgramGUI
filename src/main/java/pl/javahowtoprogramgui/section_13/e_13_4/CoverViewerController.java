package pl.javahowtoprogramgui.section_13.e_13_4;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class CoverViewerController {

    @FXML
    public ListView<Book> bookListView;
    @FXML
    public ImageView coverImageViewer;

    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public void initialize() {
        books.add(new Book("Android How to Program",
                "/images/small/androidhtp.jpg", "/images/large/androidhtp.jpg"));
        books.add(new Book("C How to Program",
                "/images/small/chtp.jpg", "/images/large/chtp.jpg"));
        books.add(new Book("C++ How to Program",
                "/images/small/cpphtp.jpg", "/images/large/cpphtp.jpg"));
        books.add(new Book("Internet and World Wide Web How to Program",
                "/images/small/iw3htp.jpg", "/images/large/iw3htp.jpg"));
        books.add(new Book("Java How to Program",
                "/images/small/jhtp.jpg", "/images/large/jhtp.jpg"));
        books.add(new Book("Visual Basic How to Program",
                "/images/small/vbhtp.jpg", "/images/large/vbhtp.jpg"));
        books.add(new Book("Visual C# How to Program",
                "/images/small/vcshtp.jpg", "/images/large/vcshtp.jpg"));

        bookListView.setItems(books);

        bookListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Book>() {
                    @Override
                    public void changed(ObservableValue<? extends Book> observableValue, Book oldValue, Book newValue) {
                        coverImageViewer.setImage(new Image(String.valueOf(getClass().getResource(newValue.getLargeImage()))));
                    }
                });

        bookListView.setCellFactory(new Callback<ListView<Book>, ListCell<Book>>() {
            @Override
            public ListCell<Book> call(ListView<Book> bookListView) {
                return new ImageTextCell();
            }
        });
    }
}
