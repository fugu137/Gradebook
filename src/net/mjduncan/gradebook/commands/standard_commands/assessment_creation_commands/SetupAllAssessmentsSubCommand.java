package net.mjduncan.gradebook.commands.standard_commands.assessment_creation_commands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import net.mjduncan.gradebook.MainController;
import net.mjduncan.gradebook.commands.standard_commands.StandardCommand;
import net.mjduncan.gradebook.model.Assessment;

public class SetupAllAssessmentsSubCommand implements StandardCommand {

    private final MainController mainController;
    private final ObservableList<Assessment> assessments;
    private final Button addAssessmentsButton;
    private final Button modifyAssessmentsButton;
    private final boolean addAssessmentsButtonWasOn;

    private ObservableList<StandardCommand> commands;

    public SetupAllAssessmentsSubCommand(MainController mainController, ObservableList<Assessment> assessments, Button addAssessmentsButton, Button modifyAssessmentsButton) {
        this.mainController = mainController;
        this.assessments = assessments;
        this.addAssessmentsButton = addAssessmentsButton;
        addAssessmentsButtonWasOn = !addAssessmentsButton.isDisabled();

        this.modifyAssessmentsButton = modifyAssessmentsButton;

        this.commands = FXCollections.observableArrayList();
    }

    @Override
    public void execute() {

        for (Assessment assessment : assessments) {
            AddAssessmentSubCommand command = new AddAssessmentSubCommand(mainController, assessment);
            command.execute();
            commands.add(command);
        }

        mainController.createTotalColumn();

        addAssessmentsButton.setDisable(true);
        modifyAssessmentsButton.setDisable(false);
        mainController.setStatusText("Assessments successfully created...", 4);
        mainController.setupStatisticsLabels();
    }

    @Override
    public void undo() {
        for (StandardCommand command : commands) {
            command.undo();
        }

        if (addAssessmentsButtonWasOn) {
            mainController.removeTotalColumn();

            addAssessmentsButton.setDisable(false);
            modifyAssessmentsButton.setDisable(true);
        }
    }

    @Override
    public void redo() {
        for (StandardCommand command : commands) {
            command.redo();
        }

        mainController.createTotalColumn();

        addAssessmentsButton.setDisable(true);
        modifyAssessmentsButton.setDisable(false);
        mainController.setStatusText("Assessments successfully created...", 4);
    }


}
