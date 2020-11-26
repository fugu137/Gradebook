package net.mjduncan.gradebook.commands.standard_commands.edit_commands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import net.mjduncan.gradebook.commands.standard_commands.StandardCommand;
import net.mjduncan.gradebook.model.Student;

public class SelectAllCommand implements StandardCommand {

    private final TableView<Student> table;
    private final ObservableList<Student> selectedStudentsCopy;

    public SelectAllCommand(TableView<Student> table) {
        this.table = table;
        selectedStudentsCopy = FXCollections.observableArrayList();
    }

    @Override
    public void execute() {
        selectedStudentsCopy.addAll(table.getSelectionModel().getSelectedItems());

        table.requestFocus();
        table.getSelectionModel().selectAll();
        table.getSelectionModel().clearSelection(table.getItems().size() - 1);
    }

    @Override
    public void undo() {
        table.getSelectionModel().clearSelection();
        selectedStudentsCopy.forEach(s -> table.getSelectionModel().select(s));
    }

    @Override
    public void redo() {
        selectedStudentsCopy.clear();
        execute();
    }
}
