package gradebook.commands;

import gradebook.enums.Gender;
import gradebook.model.Student;

public class ChangeGenderCommand implements UserCommand {

    private Student student;
    private Gender oldGender;
    private Gender newGender;

    public ChangeGenderCommand(Student student, Gender oldGender, Gender newGender) {
        this.student = student;
        this.oldGender = oldGender;
        this.newGender = newGender;
    }

    @Override
    public void execute() {
        student.setGender(newGender);
    }

    @Override
    public void undo() {
        student.setGender(oldGender);
    }

    @Override
    public void redo() {
        execute();
    }
}
