package application;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Arrays;

public class MainController {
    @FXML
    private TextArea textSpace;

    private TextFile currentTextFile;

    private Model model;

    public MainController(Model model) {
        this.model = model;
    }

    @FXML
    private void saveAction() {
        TextFile textFile = new TextFile(currentTextFile.fileGet(), Arrays.asList(textSpace.getText().split("\n")));
        model.save(textFile);
    }

    @FXML
    private void openAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            IOfile<TextFile> io = model.load(file.toPath());

            if (io.isOk() && io.hasData()) {
                currentTextFile = io.getData();

                textSpace.clear();
                currentTextFile.getText().forEach(line -> textSpace.appendText(line + "\n"));
            } else {
                System.out.println("Failed");
            }
        }
    }

    @FXML
    private void exitAction() {
        model.close();
    }

    @FXML
    private void aboutAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("Simple note-taking/text editing application developed by Vaishnav Srinidhi(19BCE0054).");
        alert.show();
    }
}
