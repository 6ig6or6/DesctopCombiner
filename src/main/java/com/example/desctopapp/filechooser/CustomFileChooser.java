package com.example.desctopapp.filechooser;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class CustomFileChooser {
    private final FileChooser fileChooser = new FileChooser();
    public File chooseFile(Stage stage) {
        return fileChooser.showOpenDialog(stage);
    }
    public List<File> chooseMultiplyFiles(Stage stage) {
        return fileChooser.showOpenMultipleDialog(stage);
    }
}
