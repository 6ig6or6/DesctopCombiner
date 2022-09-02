package com.example.desctopapp;

import com.example.desctopapp.feedback.PopupWindow;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.Desktop;
import java.util.ResourceBundle;

public class DescController {
    public ToggleGroup group;
    @SuppressWarnings(value = "all")
    private Stage stage;
    private final FileChooser fileChooser = new FileChooser();
    private static final ResourceBundle RES = ResourceBundle.getBundle("messages");
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
    @FXML
    private TextArea textArea;
    @FXML
    protected void onDivideFileButtonClick() {
        Divider divider = new Divider();
        Size size = getSize();
        textArea.appendText(createLog("file.dividing") +
                        RES.getString("pref.size") + size.getSize() + "\n");
        try {
            File file = fileChooser.showOpenDialog(stage);
            divider.divide(file, size);
            textArea.appendText(createLog("divided"));
        } catch (Exception e) {
            textArea.appendText(createLog("went.wrong"));
        }
    }
    @FXML
    protected void onCombineFileButtonClick() {
        Combiner combiner = new Combiner();
        textArea.appendText(createLog("file.combining"));
        try {
            List<File> parts = fileChooser.showOpenMultipleDialog(stage);
            combiner.combine(parts);
            textArea.appendText(createLog("combined"));
        } catch (IOException ex) {
            textArea.appendText(createLog("file.exists"));
        } catch (Exception e) {
            textArea.appendText(createLog("went.wrong"));
        }
    }
    @FXML
    protected void onLinkClick() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("https://github.com/6ig6or6"));
                textArea.appendText(createLog("redir.git"));
            } catch (IOException | URISyntaxException e) {
                textArea.appendText(createLog("went.wrong"));
            }
        }
        else {
            textArea.appendText(createLog("not.support") +
                    RES.getString("may.open"));
        }
    }
    @FXML
    protected void onSendFeedbackButtonClick() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            PopupWindow popupWindow = new PopupWindow(desktop);
            try {
                popupWindow.display();
                textArea.appendText(createLog("opening.mail"));
            } catch (RuntimeException e) {
                textArea.appendText(createLog("went.wrong"));
            }
        }
        else {
            textArea.appendText(createLog("not.support"));
        }
    }
    private Size getSize() {
        RadioButton radioButton = (RadioButton) group.getSelectedToggle();
        return switch (radioButton.getText()) {
            case "1 mb" -> Size.MB1;
            case "50 mb" -> Size.MB50;
            default -> Size.MB10;
        };
    }
    private String createLog(String message) {
        return LocalTime.now().format(df) + RES.getString(message);
    }
}