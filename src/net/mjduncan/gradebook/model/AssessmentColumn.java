package net.mjduncan.gradebook.model;

import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;

public class AssessmentColumn<Student, Number> extends TableColumn<Student, Number> {

    private Assessment assessment;

    public AssessmentColumn(String name) {
        this(name, null);
    }

    public AssessmentColumn(String name, Assessment assessment) {
        super(name);
        this.assessment = assessment;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    @Override
    public String toString() {
        return getText();
    }

    public VBox assessmentInfoBox() {
        return assessment.infoBox();
    }
}


