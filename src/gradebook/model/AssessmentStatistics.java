package gradebook.model;

import gradebook.tools.NumberRounder;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssessmentStatistics extends Statistics {

    private Assessment assessment;

    public AssessmentStatistics(ObservableList<Student> classStudents, Assessment assessment) {
        super(classStudents);
        this.assessment = assessment;
    }

    @Override
    void updateStatistics() {
        List<Double> grades = new ArrayList<>();

        if (this.assessment instanceof StdAssessment) {
            StdAssessment stdAssessment = (StdAssessment) assessment;

            for (Student s : getClassStudents()) {
                if (s.stdAssessmentGradeProperty(stdAssessment) != null && s.stdAssessmentGradeProperty(stdAssessment).getValue() != null) {
                    Double grade = NumberRounder.round(s.stdAssessmentGradeProperty(stdAssessment).getValue(), 1);
                    grades.add(grade);
                }
            }

        } else if (this.assessment instanceof AssessmentSet) {
            AssessmentSet assessmentSet = (AssessmentSet) assessment;

            for (Student s : getClassStudents()) {
                if (s.assessmentSetTotalGradeProperty(assessmentSet) != null && s.assessmentSetTotalGradeProperty(assessmentSet).getValue() != null) {
                    grades.add(s.assessmentSetTotalGradeProperty(assessmentSet).getValue());
                }
            }

        } else {
            System.out.println("Invalid assessment type!");
        }

        Collections.sort(grades);

        lowestGradeProperty().set(grades.get(0));
        highestGradeProperty().set(grades.get(grades.size() - 1));

        super.setMedian(grades);
        super.setAverage(grades);
    }


}
