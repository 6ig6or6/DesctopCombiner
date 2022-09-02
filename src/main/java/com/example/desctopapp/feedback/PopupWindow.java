package com.example.desctopapp.feedback;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;


public class PopupWindow {
    private final Desktop desktop;
    private static final ResourceBundle RES = ResourceBundle.getBundle("messages");
    public PopupWindow(Desktop desktop) {
        this.desktop = desktop;
    }
    public void display() {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle(RES.getString("feedback.button"));
        TextArea textArea = createTextArea();
        Label label = new Label(RES.getString("open.mail.button"));
        Button button = new Button(RES.getString("send"));

        button.setOnAction(actionEvent -> {
            try {
                String body = textArea.getText().replace("\s", "%20");
                desktop.mail(new URI("mailto:gyupper1@gmail.com?subject=Feedback&body="+body));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            } finally {
                popupWindow.close();
            }
        });

        VBox layout = new VBox(10);

        layout.getChildren().addAll(textArea, label, button);

        layout.setAlignment(Pos.BOTTOM_CENTER);
        layout.setPadding(new Insets(15));
        Scene scene = new Scene(layout, 500, 400);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }
    private static TextArea createTextArea() {
        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setLayoutX(10);
        textArea.setMinSize(350, 300);
        return textArea;
    }
}
