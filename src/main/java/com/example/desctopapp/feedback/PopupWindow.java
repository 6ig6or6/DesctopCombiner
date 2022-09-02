package com.example.desctopapp.feedback;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.awt.*;
import java.io.IOException;


public class PopupWindow {
    private Desktop desktop;

    public PopupWindow(Desktop desktop) {
        this.desktop = desktop;
    }

    public void display()
    {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Feedback");

        Label label = new Label("This button will open your mail application to send the feedback");

        Button button= new Button("Send");

        button.setOnAction(actionEvent -> {
            try {
                desktop.mail();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                popupWindow.close();
            }
        });

                VBox layout = new VBox(10);

        layout.getChildren().addAll(label, button);

        layout.setAlignment(Pos.BOTTOM_CENTER);
        layout.setPadding(new Insets(15));
        Scene scene1 = new Scene(layout, 500, 425);

        popupWindow.setScene(scene1);

        popupWindow.showAndWait();

    }

}
