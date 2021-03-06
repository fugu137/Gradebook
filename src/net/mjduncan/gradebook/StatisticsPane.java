package net.mjduncan.gradebook;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import net.mjduncan.gradebook.model.AssessmentColumn;
import net.mjduncan.gradebook.model.Student;
import net.mjduncan.gradebook.model.StudentGroup;
import net.mjduncan.gradebook.tools.CourseManager;


public class StatisticsPane extends VBox {

    private VBox chartBox;
    private Button closeButton;

    private BarChart<String, Number> barChart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;

    private PieChart pieChart;

    public StatisticsPane() {
        setStyles();
        addCharts();
        addFooter();

        setVgrow(chartBox, Priority.ALWAYS);
        setPrefWidth(370);
    }

    private void setStyles() {
        this.getStyleClass().add("bordered-white-pane");
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(30);
        this.setPadding(new Insets(0, -5, 12, -5));
    }

    public void addCharts() {
        chartBox = new VBox();
        chartBox.setAlignment(Pos.TOP_CENTER);
        chartBox.setSpacing(20);

        this.xAxis = new CategoryAxis();
        this.yAxis = new NumberAxis();

        xAxis.setLabel("Grades");
        yAxis.setLabel("Percentage of Students");

        barChart = new BarChart<>(xAxis, yAxis);
        yAxis.setAnimated(false);

        pieChart = new PieChart();
        pieChart.setAnimated(false);

        chartBox.getChildren().add(barChart);
        chartBox.getChildren().add(pieChart);

        this.getChildren().add(chartBox);
    }

    private void addFooter() {
        HBox footer = new HBox();
        footer.setAlignment(Pos.BOTTOM_CENTER);
        footer.setPrefHeight(10);

        closeButton = new Button("Close");
        closeButton.getStyleClass().add("chart-button");
        footer.getChildren().add(closeButton);

        this.getChildren().add(footer);
    }



    public void fillBarChart(CourseManager courseManager, AssessmentColumn<Student, ?> totalColumn, ComboBox<StudentGroup> classComboBox, ComboBox<AssessmentColumn<Student, ?>> columnComboBox) {
        StudentGroup selectedGroup = classComboBox.getSelectionModel().getSelectedItem();
        AssessmentColumn<Student, ?> selectedColumn = columnComboBox.getSelectionModel().getSelectedItem();

        if (selectedGroup != null && selectedColumn != null) {

            if (selectedGroup == courseManager.getCourseCohort()) {
                if (selectedColumn == totalColumn) {
                    courseManager.fillBarChartWithOverallGrades(barChart);

                } else {
                    if (selectedColumn.getAssessment() != null) {
                        courseManager.fillBarChartWithAssessmentGrades(barChart, selectedColumn.getAssessment());
                    } else {
                        System.out.println("No assessment found for column " + selectedColumn.getText());
                    }
                }
            } else {
                if (selectedColumn == totalColumn) {
                    selectedGroup.fillBarChartWithOverallGrades(barChart);

                } else {
                    if (selectedColumn.getAssessment() != null) {
                        selectedGroup.fillBarChartWithAssessmentGrades(barChart, selectedColumn.getAssessment());
                    } else {
                        System.out.println("No assessment found for column " + selectedColumn.getText());
                    }
                }
            }

            addTooltips();

        } else {
            System.out.println("Class or column not selected [statistics tab]");
        }
    }

    private void addTooltips() {

        for (XYChart.Series<String, Number> s : barChart.getData()) {
            for (XYChart.Data<String, Number> data : s.getData()) {
                StringProperty textProperty = new SimpleStringProperty();
                textProperty.bind(data.YValueProperty().asString().concat("%"));

                Tooltip tooltip = new Tooltip();
                tooltip.textProperty().bind(textProperty);

                Tooltip.install(data.getNode(), tooltip);
            }
        }
    }

    public void fillPieChart(CourseManager courseManager) {
        courseManager.fillPieChart(pieChart);
    }

    public void replaceAndFillBarChart(CourseManager courseManager, AssessmentColumn<Student, ?> totalColumn, ComboBox<StudentGroup> classComboBox, ComboBox<AssessmentColumn<Student, ?>> columnComboBox) {
        chartBox.getChildren().remove(barChart);

        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        barChart = new BarChart<>(xAxis, yAxis);
        yAxis.setAnimated(false);

        chartBox.getChildren().add(0, barChart);

        fillBarChart(courseManager, totalColumn, classComboBox, columnComboBox);
    }

    public Button getCloseButton() {
        return closeButton;
    }

}
