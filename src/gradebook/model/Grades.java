package gradebook.model;

import gradebook.tools.NumberRounder;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import java.util.Collection;
import java.util.LinkedHashMap;

public class Grades {

    private ObservableMap<Assessment, AssessmentData> grades;
    private ObjectProperty<Double> totalGrade;

    public Grades() {
        this.grades = FXCollections.observableMap(new LinkedHashMap<>());
        this.totalGrade = new SimpleObjectProperty<>(null);
    }

    private void updateTotalGrade() {
        Double total = null;
        Double totalWeighting = null;

        for (AssessmentData a : grades.values()) {
            if (a.gradeProperty() != null && a.gradeProperty().getValue() != null) {

                if (total == null) {
                    total = 0.0;
                }
                if (totalWeighting == null) {
                    totalWeighting = 0.0;
                }

                Double assessmentWeighting = a.getAssessment().getWeighting();
                Double grade = (double) a.getGrade();

                total = total + NumberRounder.round(grade * assessmentWeighting, 1);
                totalWeighting = totalWeighting + assessmentWeighting;
            }
        }

        if (total == null) {
            totalGrade.set(null);
        } else {
            totalGrade.set(NumberRounder.round(total / totalWeighting, 1));
        }
    }

    public void add(Assessment assessment, AssessmentData assessmentData) {
        grades.putIfAbsent(assessment, assessmentData);
    }

    public AssessmentData get(Assessment assessment) {
        return grades.get(assessment);
    }

    public Collection<AssessmentData> assessmentDataList() {
        return grades.values();
    }
}
