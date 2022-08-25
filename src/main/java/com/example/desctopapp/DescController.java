package com.example.desctopapp;

import com.example.desctopapp.filechooser.CustomFileChooser;
import com.example.desctopapp.model.Combiner;
import com.example.desctopapp.model.Divider;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class DescController {
    private Stage stage;
    private final CustomFileChooser fileChooser = new CustomFileChooser();
    @FXML
    private Label operationText;



    @FXML
    protected void onDivideFileButtonClick() {
        Divider divider = new Divider();
        operationText.setText("The file is dividing!");
        try {
            File file = fileChooser.chooseFile(stage);
            divider.divide(file);
            operationText.setText("Done!");
        } catch (Exception e) {
            operationText.setText("Something went wrong!");
        }

    }
    @FXML
    protected void onCombineFileButtonClick() {
        Combiner combiner = new Combiner();
        operationText.setText("The file is combining!");
        try {
            List<File> parts = fileChooser.chooseMultiplyFiles(stage);
            combiner.combine(parts);
            operationText.setText("Done!");
        } catch (Exception e) {
            operationText.setText("Something went wrong!");
        }
    }
}