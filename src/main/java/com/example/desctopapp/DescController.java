package com.example.desctopapp;

import com.example.desctopapp.filechooser.CustomFileChooser;
import com.example.desctopapp.model.Combiner;
import com.example.desctopapp.model.Divider;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class DescController {
    private Stage stage;
    private final CustomFileChooser fileChooser = new CustomFileChooser();
    @FXML
    private TextArea textArea;

    @FXML
    protected void onDivideFileButtonClick() {
        Divider divider = new Divider();
        textArea.appendText("The file is dividing!\n");
        try {
            File file = fileChooser.chooseFile(stage);
            divider.divide(file);
            textArea.appendText("Done!\n");
        } catch (Exception e) {
            textArea.appendText("Something went wrong!\n");
        }

    }
    @FXML
    protected void onCombineFileButtonClick() {
        Combiner combiner = new Combiner();
        textArea.appendText("The file is combining!\n");
        try {
            List<File> parts = fileChooser.chooseMultiplyFiles(stage);
            combiner.combine(parts);
            textArea.appendText("Done!\n");
        } catch (Exception e) {
            textArea.appendText("Something went wrong!\n");
        }
    }
}