package gradebook.commands;

import gradebook.MainController;
import gradebook.model.CourseManager;
import gradebook.model.Student;
import javafx.collections.ObservableList;

public class AddNewStudentCommand implements UserCommand{

    private MainController mainController;
    private CourseManager courseManager;
    private ObservableList<Student> tableItems;
    private Student student;

    public AddNewStudentCommand(MainController mainController, Student student) {
        this.mainController = mainController;
        this.courseManager = mainController.getCourseManager();
        this.tableItems = mainController.getTable().getItems();
        this.student = student;
    }

    @Override
    public void execute() {
        courseManager.newStudent(student);
        mainController.newBlankStudent();
    }

    @Override
    public void undo() {
        courseManager.removeStudent(student);
        tableItems.remove(student);
    }

    @Override
    public void redo() {
        courseManager.newStudent(student);
        tableItems.add(tableItems.size() - 1, student);
    }
}
