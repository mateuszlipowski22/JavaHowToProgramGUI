package pl.javahowtoprogramgui.section_13.e_13_4;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ImageTextCell extends ListCell<Book> {
    private VBox vBox = new VBox(9.0);
    private ImageView thumbImageView = new ImageView();
    private Label label = new Label();

    public ImageTextCell() {
        vBox.setAlignment(Pos.CENTER);

        thumbImageView.setPreserveRatio(true);
        thumbImageView.setFitHeight(100.0);
        vBox.getChildren().add(thumbImageView);

        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        vBox.getChildren().add(label);

        setPrefWidth(USE_PREF_SIZE);
    }

    @Override
    protected void updateItem(Book item, boolean empty) {
        super.updateItem(item, empty);

        if(empty || item==null){
            setGraphic(null);
        }else {
            thumbImageView.setImage(new Image(item.getThumbImage()));
            label.setText(item.getTitle());
            setGraphic(vBox);
        }
    }
}
