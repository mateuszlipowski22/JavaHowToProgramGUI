package pl.javahowtoprogramgui.section_22.e_22_8;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import org.controlsfx.dialog.ExceptionDialog;

import java.net.URL;

public class VideoPlayerController {

    @FXML
    private MediaView mediaView;
    @FXML
    private Button playPauseButton;

    private MediaPlayer mediaPlayer;
    private boolean playing = false;

    public void initialize() {

        URL url = VideoPlayerController.class.getResource("sts117.mp4");
        Media media = new Media(url.toExternalForm());

        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setOnEndOfMedia(
                () -> {
                    playing = false;
                    playPauseButton.setText("Odtwarzaj");
                    mediaPlayer.seek(Duration.ZERO);
                    mediaPlayer.pause();
                }
        );

        mediaPlayer.setOnError(
                () -> {
                    ExceptionDialog dialog = new ExceptionDialog(mediaPlayer.getError());
                    dialog.showAndWait();
                }
        );

        mediaPlayer.setOnReady(
                () -> {
                    DoubleProperty width = mediaView.fitWidthProperty();
                    DoubleProperty height = mediaView.fitHeightProperty();
                    width.bind(Bindings.selectDouble(
                            mediaView.sceneProperty(),"width"));
                    height.bind(Bindings.selectDouble(
                            mediaView.sceneProperty(),"height"));
                }
        );
    }

    @FXML
    private void playPauseButtonPressed(ActionEvent e){
        playing = !playing;
        if(playing){
            playPauseButton.setText("Pauza");
            mediaPlayer.play();
        }else {
            playPauseButton.setText("Odtwarzaj");
            mediaPlayer.pause();
        }
    }
}
