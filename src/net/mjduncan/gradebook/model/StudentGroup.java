package net.mjduncan.gradebook.model;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;

import java.util.List;

public interface StudentGroup {

    String getName();
    ObservableList<Student> getStudents();
    Statistics getStatistics(Assessment assessment);
    Statistics getTotalStatistics();
    ObservableList<Assessment> getAssessments();
    Integer getNumberAttempted();
    Integer getNumberAttempted(Assessment assessment);
    int getNumberOfStudents();
    void addAssessment(Assessment assessment);
    void removeAssessment(Assessment assessment);
    void addSubAssessment(AssessmentSet assessmentSet, StdAssessment subAssessment);
    void removeSubAssessments(AssessmentSet set, List<StdAssessment> toRemove);
    ObjectProperty<Double> totalGradeMedianProperty();
    ObjectProperty<Double> totalGradeAverageProperty();
    ObjectProperty<Double> totalGradeHighestProperty();
    ObjectProperty<Double> totalGradeLowestProperty();
    ObjectProperty<Double> assessmentMedianProperty(Assessment assessment);
    ObjectProperty<Double> assessmentAverageProperty(Assessment assessment);
    ObjectProperty<Double> assessmentHighestProperty(Assessment assessment);
    ObjectProperty<Double> assessmentLowestProperty(Assessment assessment);
    void fillBarChartWithOverallGrades(BarChart<String, Number> barChart);
    void fillBarChartWithAssessmentGrades(BarChart<String, Number> barChart, Assessment assessment);
    ObjectProperty<Integer> numberAttemptedProperty();
    ObjectProperty<Integer> numberAttemptedProperty(Assessment assessment);





}
