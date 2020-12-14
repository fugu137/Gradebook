package net.mjduncan.gradebook.commands.standard_commands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Window;
import net.mjduncan.gradebook.MainController;
import net.mjduncan.gradebook.model.Student;
import net.mjduncan.gradebook.tools.CourseManager;
import net.mjduncan.gradebook.tools.FileChooserWindow;
import net.mjduncan.gradebook.tools.StudentImporter;

import java.io.File;
import java.util.List;

public class ImportStudentsCommand implements StandardCommand {

    private final CourseManager courseManager;
    private final TableView<Student> table;
    private final Student blankStudent;
    private final Window window;
    private final ObservableList<Student> tableStudentsCopy;
    private ObservableList<Student> tableStudentsForRedo;

    public ImportStudentsCommand(MainController mainController, Window window) {
        this.courseManager = mainController.getCourseManager();
        this.table = mainController.getTable();
        this.blankStudent = mainController.getBlankStudent();
        this.window = window;
        this.tableStudentsCopy = FXCollections.observableArrayList();
    }

    @Override
    public void execute() {
        tableStudentsCopy.addAll(table.getItems());
        tableStudentsCopy.remove(blankStudent);

        List<File> files = FileChooserWindow.displayImportWindow(window, "Choose a file to import from", true);

        for (File file : files) {
            ObservableList<Student> students = StudentImporter.importStudents(file);
            courseManager.newStudents(students);

            table.getItems().addAll(students);
            table.sort();
        }
    }

    @Override
    public void undo() {
        tableStudentsForRedo = FXCollections.observableArrayList();
        tableStudentsForRedo.addAll(table.getItems());
        tableStudentsForRedo.remove(blankStudent);

        courseManager.clear();
        table.getItems().clear();

        courseManager.newStudents(tableStudentsCopy);

        table.getItems().addAll(tableStudentsCopy);
        table.getItems().add(blankStudent);
//        courseManager.getClasses().forEach(c -> {
//            System.out.println("Class: " + c.getName());
//            c.getStudents().forEach(System.out::println);
//        });
    }

    @Override
    public void redo() {
        courseManager.clear();
        courseManager.newStudents(tableStudentsForRedo);

        table.getItems().clear();
        table.getItems().addAll(tableStudentsForRedo);
        table.getItems().add(blankStudent);
    }
}
