package gradebook.commands.primitive_commands;

import gradebook.MainController;
import gradebook.tools.FileManager;
import javafx.beans.property.ObjectProperty;
import javafx.stage.Stage;

import java.io.File;

public class SaveGradebookAsCommand implements PrimitiveCommand {

    private MainController mainController;
    private ObjectProperty<FileManager> fileManager;
    private File file;
    private Stage stage;

    public SaveGradebookAsCommand(MainController mainController, ObjectProperty<FileManager> fileManager, File file, Stage stage) {
        this.mainController = mainController;
        this.fileManager = fileManager;
        this.file = file;
        this.stage = stage;
    }

    @Override
    public void execute() {
        fileManager.set(new FileManager());
        fileManager.getValue().saveAs(file, mainController);

        stage.setTitle("Gradebook - " + file.getName());
    }
}
