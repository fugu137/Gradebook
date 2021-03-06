package net.mjduncan.gradebook;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import net.mjduncan.gradebook.commands.standard_commands.assessment_creation_commands.SetupAllAssessmentsSubCommand;
import net.mjduncan.gradebook.commands.standard_commands.assessment_creation_commands.SubmitAssessmentsCommand;
import net.mjduncan.gradebook.enums.AssessmentForm;
import net.mjduncan.gradebook.enums.AssessmentType;
import net.mjduncan.gradebook.model.Assessment;
import net.mjduncan.gradebook.model.AssessmentCreationBar;
import net.mjduncan.gradebook.tools.CommandManager;
import net.mjduncan.gradebook.tools.Formatter;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AssessmentCreationController implements Initializable {
    @FXML
    private ComboBox<AssessmentType> comboBox1;
    @FXML
    private ComboBox<AssessmentForm> modalityBox1;
    @FXML
    private Button clearButton1;
    @FXML
    private TextField nameField1;
    @FXML
    private TextField quantityField1;
    @FXML
    private TextField bestOfField1;
    @FXML
    private TextField weightField1;

    @FXML
    private ComboBox<AssessmentType> comboBox2;
    @FXML
    private ComboBox<AssessmentForm> modalityBox2;
    @FXML
    private Button clearButton2;
    @FXML
    private TextField nameField2;
    @FXML
    private TextField quantityField2;
    @FXML
    private TextField bestOfField2;
    @FXML
    private TextField weightField2;

    @FXML
    private ComboBox<AssessmentType> comboBox3;
    @FXML
    private ComboBox<AssessmentForm> modalityBox3;
    @FXML
    private Button clearButton3;
    @FXML
    private TextField nameField3;
    @FXML
    private TextField quantityField3;
    @FXML
    private TextField bestOfField3;
    @FXML
    private TextField weightField3;

    @FXML
    private ComboBox<AssessmentType> comboBox4;
    @FXML
    private ComboBox<AssessmentForm> modalityBox4;
    @FXML
    private Button clearButton4;
    @FXML
    private TextField nameField4;
    @FXML
    private TextField quantityField4;
    @FXML
    private TextField bestOfField4;
    @FXML
    private TextField weightField4;

    @FXML
    private ComboBox<AssessmentType> comboBox5;
    @FXML
    private ComboBox<AssessmentForm> modalityBox5;
    @FXML
    private Button clearButton5;
    @FXML
    private TextField nameField5;
    @FXML
    private TextField quantityField5;
    @FXML
    private TextField bestOfField5;
    @FXML
    private TextField weightField5;

    @FXML
    private ComboBox<AssessmentType> comboBox6;
    @FXML
    private ComboBox<AssessmentForm> modalityBox6;
    @FXML
    private Button clearButton6;
    @FXML
    private TextField nameField6;
    @FXML
    private TextField quantityField6;
    @FXML
    private TextField bestOfField6;
    @FXML
    private TextField weightField6;

    @FXML
    private ComboBox<AssessmentType> comboBox7;
    @FXML
    private ComboBox<AssessmentForm> modalityBox7;
    @FXML
    private Button clearButton7;
    @FXML
    private TextField nameField7;
    @FXML
    private TextField quantityField7;
    @FXML
    private TextField bestOfField7;
    @FXML
    private TextField weightField7;

    @FXML
    private ComboBox<AssessmentType> comboBox8;
    @FXML
    private ComboBox<AssessmentForm> modalityBox8;
    @FXML
    private Button clearButton8;
    @FXML
    private TextField nameField8;
    @FXML
    private TextField quantityField8;
    @FXML
    private TextField bestOfField8;
    @FXML
    private TextField weightField8;

    @FXML
    private TextField totalWeightField;

    @FXML
    private Button submitButton;
    @FXML
    private Button cancelButton;

    //Control//
    private MainController mainController;
    private CommandManager commandManager;
    private final ObservableList<AssessmentCreationBar> assessmentCreationBars = FXCollections.observableArrayList();

    NumberStringConverter converter = new NumberStringConverter();

    IntegerProperty weight1AsInt = new SimpleIntegerProperty();
    IntegerProperty weight2AsInt = new SimpleIntegerProperty();
    IntegerProperty weight3AsInt = new SimpleIntegerProperty();
    IntegerProperty weight4AsInt = new SimpleIntegerProperty();
    IntegerProperty weight5AsInt = new SimpleIntegerProperty();
    IntegerProperty weight6AsInt = new SimpleIntegerProperty();
    IntegerProperty weight7AsInt = new SimpleIntegerProperty();
    IntegerProperty weight8AsInt = new SimpleIntegerProperty();
    IntegerProperty totalWeightAsInt = new SimpleIntegerProperty();

    ObservableList<Assessment> assessmentsToRemove = FXCollections.observableArrayList();

    ObservableList<String> errorMessages = FXCollections.observableArrayList();


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public ObservableList<Assessment> getAssessmentsToRemove() {
        return assessmentsToRemove;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillComboBoxes();
        addAssessmentCreationBars();
        bindTotalWeightingField();
        formatWeightingFields();
    }

    //Initialize Methods//
    private void fillComboBoxes() {
        List<AssessmentType> assessmentList = Arrays.asList(AssessmentType.values());
        comboBox1.getItems().addAll(assessmentList);
        comboBox2.getItems().addAll(assessmentList);
        comboBox3.getItems().addAll(assessmentList);
        comboBox4.getItems().addAll(assessmentList);
        comboBox5.getItems().addAll(assessmentList);
        comboBox6.getItems().addAll(assessmentList);
        comboBox7.getItems().addAll(assessmentList);
        comboBox8.getItems().addAll(assessmentList);

        List<AssessmentForm> assessmentFormList = Arrays.asList(AssessmentForm.values());
        modalityBox1.getItems().addAll(assessmentFormList);
        modalityBox2.getItems().addAll(assessmentFormList);
        modalityBox3.getItems().addAll(assessmentFormList);
        modalityBox4.getItems().addAll(assessmentFormList);
        modalityBox5.getItems().addAll(assessmentFormList);
        modalityBox6.getItems().addAll(assessmentFormList);
        modalityBox7.getItems().addAll(assessmentFormList);
        modalityBox8.getItems().addAll(assessmentFormList);
    }

    private void addAssessmentCreationBars() {
        AssessmentCreationBar bar1 = new AssessmentCreationBar(modalityBox1, comboBox1, clearButton1, nameField1, quantityField1, bestOfField1, weightField1);
        assessmentCreationBars.add(bar1);
        AssessmentCreationBar bar2 = new AssessmentCreationBar(modalityBox2, comboBox2, clearButton2, nameField2, quantityField2, bestOfField2, weightField2);
        assessmentCreationBars.add(bar2);
        AssessmentCreationBar bar3 = new AssessmentCreationBar(modalityBox3, comboBox3, clearButton3, nameField3, quantityField3, bestOfField3, weightField3);
        assessmentCreationBars.add(bar3);
        AssessmentCreationBar bar4 = new AssessmentCreationBar(modalityBox4, comboBox4, clearButton4, nameField4, quantityField4, bestOfField4, weightField4);
        assessmentCreationBars.add(bar4);
        AssessmentCreationBar bar5 = new AssessmentCreationBar(modalityBox5, comboBox5, clearButton5, nameField5, quantityField5, bestOfField5, weightField5);
        assessmentCreationBars.add(bar5);
        AssessmentCreationBar bar6 = new AssessmentCreationBar(modalityBox6, comboBox6, clearButton6, nameField6, quantityField6, bestOfField6, weightField6);
        assessmentCreationBars.add(bar6);
        AssessmentCreationBar bar7 = new AssessmentCreationBar(modalityBox7, comboBox7, clearButton7, nameField7, quantityField7, bestOfField7, weightField7);
        assessmentCreationBars.add(bar7);
        AssessmentCreationBar bar8 = new AssessmentCreationBar(modalityBox8, comboBox8, clearButton8, nameField8, quantityField8, bestOfField8, weightField8);
        assessmentCreationBars.add(bar8);
    }

    private void bindTotalWeightingField() {
        BooleanBinding allDisabled = comboBox1.getSelectionModel().selectedItemProperty().isNull()
                .and(comboBox2.getSelectionModel().selectedItemProperty().isNull())
                .and(comboBox3.getSelectionModel().selectedItemProperty().isNull())
                .and(comboBox4.getSelectionModel().selectedItemProperty().isNull())
                .and(comboBox5.getSelectionModel().selectedItemProperty().isNull())
                .and(comboBox6.getSelectionModel().selectedItemProperty().isNull())
                .and(comboBox7.getSelectionModel().selectedItemProperty().isNull())
                .and(comboBox8.getSelectionModel().selectedItemProperty().isNull());
        totalWeightField.disableProperty().bind(allDisabled);

        submitButton.disableProperty().bind(allDisabled);

        weightField1.textProperty().bindBidirectional(weight1AsInt, converter);
        weightField2.textProperty().bindBidirectional(weight2AsInt, converter);
        weightField3.textProperty().bindBidirectional(weight3AsInt, converter);
        weightField4.textProperty().bindBidirectional(weight4AsInt, converter);
        weightField5.textProperty().bindBidirectional(weight5AsInt, converter);
        weightField6.textProperty().bindBidirectional(weight6AsInt, converter);
        weightField7.textProperty().bindBidirectional(weight7AsInt, converter);
        weightField8.textProperty().bindBidirectional(weight8AsInt, converter);

        NumberBinding sum = Bindings.add(weight1AsInt, weight2AsInt).add(weight3AsInt).add(weight4AsInt)
                .add(weight5AsInt).add(weight6AsInt).add(weight7AsInt).add(weight8AsInt);
        totalWeightAsInt.bind(sum);
        totalWeightField.textProperty().bind(totalWeightAsInt.asString());
    }

    private void formatWeightingFields() {
        Formatter.formatWeightingBox(weightField1);
        Formatter.formatWeightingBox(weightField2);
        Formatter.formatWeightingBox(weightField3);
        Formatter.formatWeightingBox(weightField4);
        Formatter.formatWeightingBox(weightField5);
        Formatter.formatWeightingBox(weightField6);
        Formatter.formatWeightingBox(weightField7);
        Formatter.formatWeightingBox(weightField8);
        Formatter.formatWeightingBox(totalWeightField);

        weightField1.setText(null);
        weightField2.setText(null);
        weightField3.setText(null);
        weightField4.setText(null);
        weightField5.setText(null);
        weightField6.setText(null);
        weightField7.setText(null);
        weightField8.setText(null);
    }


    //Assessment Creation Window Methods//
    public void addAssessment(Assessment assessment) {
        for (AssessmentCreationBar bar : assessmentCreationBars) {

            if (!bar.isActive()) {
                bar.addAssessment(assessment);
                break;
            }
        }
    }

    @FXML
    public void submitButtonPressed() {
        errorMessages.clear();

        boolean unableToSubmit = false;
        Integer totalWeighting = null;

        for (AssessmentCreationBar bar : assessmentCreationBars) {

            if (bar.hasInvalidEntries()) {
                unableToSubmit = true;
                bar.getErrorMessages().forEach(s -> {
                    if (!errorMessages.contains(s)) {
                        errorMessages.add(s);
                    }
                });

            } else if (bar.isActive()) {

                Integer barWeighting = bar.getWeighting();

                if (barWeighting == null) {
                    barWeighting = 0;
                }
                if (totalWeighting == null) {
                    totalWeighting = 0;
                }

                totalWeighting = totalWeighting + barWeighting;

            }
        }

        if (totalWeighting != null && !totalWeighting.equals(100)) {
            unableToSubmit = true;
            errorMessages.add("- Weightings must sum to 100.");
            for (AssessmentCreationBar bar : assessmentCreationBars) {
                if (!bar.getWeightingField().isDisabled()) {
                    bar.getWeightingField().getStyleClass().add("error-number-field");
                }
            }
        }

        if (unableToSubmit) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("dialog-pane.css").toExternalForm());
            alert.setTitle("Invalid Entries");
            alert.setHeaderText("Some entries are invalid! Please try again.");

            StringBuilder content = new StringBuilder();
            String filler = "";
            for (String s : errorMessages) {
                content.append(filler).append(s);
                filler = "\n";
            }
            alert.setContentText(content.toString());
            alert.showAndWait();

        } else {
            commandManager.execute(new SubmitAssessmentsCommand(mainController, this, assessmentCreationBars), true);

            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        }
    }

    public void submitAssessmentsWithoutClick() {
        ObservableList<Assessment> assessments = FXCollections.observableArrayList();

        for (AssessmentCreationBar bar : assessmentCreationBars) {
            if (bar.getAssessment() != null) {
                assessments.add(bar.getAssessment());
                bar.getFormComboBox().setDisable(true);
            }
        }
        Button addAssessmentsButton = mainController.getAddAssessmentsButton();
        Button modifyAssessmentsButton = mainController.getModifyAssessmentsButton();

        commandManager.execute(new SetupAllAssessmentsSubCommand(mainController, assessments, addAssessmentsButton, modifyAssessmentsButton), true);
    }

    @FXML
    public void clearButtonPressed(ActionEvent event) {
        Button button = (Button) event.getSource();
        AssessmentCreationBar bar = (AssessmentCreationBar) button.getUserData();

        submitButton.disableProperty().unbind();

        Alert popup = new Alert(Alert.AlertType.CONFIRMATION);
        popup.getDialogPane().getStylesheets().add(getClass().getResource("dialog-pane.css").toExternalForm());
        popup.setHeaderText("Are you sure you want to remove this assessment?");
        popup.setContentText("Clicking 'OK' will remove the assessment from the assessment modification window. When the 'Submit' button is pressed, the assessment will be deleted from the gradebook, and any grades for the assessment will be lost.");

        if (popup.showAndWait().isPresent() && popup.getResult() == ButtonType.OK) {
            Assessment assessment = bar.clear();
            assessmentsToRemove.add(assessment);
        }
    }
}
