package com.example.desctopapp;

import com.example.desctopapp.model.Combiner;
import com.example.desctopapp.model.Divider;
import com.example.desctopapp.model.Size;
import javafx.fxml.FXML;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.List;
import java.awt.Desktop;

public class DescController {
    public ToggleGroup group;
    private Stage stage;
    private final FileChooser fileChooser = new FileChooser();
    @FXML
    private TextArea textArea;
    @FXML
    protected void onDivideFileButtonClick() {
        Divider divider = new Divider();
        Size size = getSize();
        textArea.appendText(LocalTime.now() + ": The file is dividing! " +
                        "Preferred size is " + size.getSize() + "\n");
        try {
            File file = fileChooser.showOpenDialog(stage);
            divider.divide(file, size);
            textArea.appendText(LocalTime.now() +": File divided!\n");
        } catch (Exception e) {
            textArea.appendText(LocalTime.now() +": Something went wrong!\n");
        }
    }
    @FXML
    protected void onCombineFileButtonClick() {
        Combiner combiner = new Combiner();
        textArea.appendText(LocalTime.now() +": The file is combining!\n");
        try {
            List<File> parts = fileChooser.showOpenMultipleDialog(stage);
            combiner.combine(parts);
            textArea.appendText(LocalTime.now() +": File combined!\n");
        } catch (IOException ex) {
            textArea.appendText(LocalTime.now() +": File already exists!\n");
        } catch (Exception e) {
            textArea.appendText(LocalTime.now() +": Something went wrong!\n");
        }
    }
    @FXML
    protected void onLinkClick() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("https://github.com/6ig6or6"));
                textArea.appendText(LocalTime.now() + ": Redirecting to GitHub...\n");
            } catch (IOException | URISyntaxException e) {
                textArea.appendText(LocalTime.now() + ": Something went wrong!\n");
            }
        }
        else {
            textArea.appendText(LocalTime.now() + ": Unfortunately, your system doesn't support this operation" +
                    "but you may open this link in a browser\n");
        }
    }
    @FXML
    protected void onSendFeedbackButtonClick() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.mail();
                textArea.appendText(LocalTime.now() + ": Opening your mail application...\n");
            } catch (IOException e) {
                textArea.appendText(LocalTime.now() + ": Something went wrong!\n");
            }
        }
    }
    private Size getSize() {
        RadioButton radioButton = (RadioButton) group.getSelectedToggle();
        return switch (radioButton.getText()) {
            case "1 mb" -> Size.MB1;
            case "10 mb" -> Size.MB10;
            case "50 mb" -> Size.MB50;
            default -> Size.MB10;
        };
    }
}