package gradebook.tools;

import gradebook.commands.primitive_commands.PrimitiveCommand;
import gradebook.commands.refresh_commands.RefreshCommand;
import gradebook.commands.standard_commands.StandardCommand;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandManager {

    Deque<StandardCommand> undoStack;
    Deque<StandardCommand> redoStack;
    BooleanProperty undoStackEmpty;
    BooleanProperty redoStackEmpty;

    public CommandManager() {
        undoStack = new ArrayDeque<>();
        redoStack = new ArrayDeque<>();
        undoStackEmpty = new SimpleBooleanProperty(true);
        redoStackEmpty = new SimpleBooleanProperty(true);
    }

    public void execute(StandardCommand command, boolean saveToHistory) {
        command.execute();

        if (saveToHistory) {
            addToUndoStack(command);
        }
    }

    public void execute(RefreshCommand command) {
        command.execute();

        undoStack.clear();
        redoStack.clear();
    }

    public void execute(PrimitiveCommand command) {
        command.execute();
    }

    public void undo() {
        if (undoStack.size() > 0) {
            StandardCommand command = getFromUndoStack();
            addToRedoStack(command);

            command.undo();
        }
    }

    public void redo() {
        if (redoStack.size() > 0) {
            StandardCommand command = getFromRedoStack();
            addToUndoStack(command);

            command.redo();
        }
    }

    private void addToUndoStack(StandardCommand command) {
        undoStack.push(command);
        undoStackEmpty.set(false);
    }

    private StandardCommand getFromUndoStack() {
        StandardCommand command = undoStack.pop();

        if (undoStack.size() == 0) {
            undoStackEmpty.set(true);
        }

        return command;
    }

    private void addToRedoStack(StandardCommand command) {
        redoStack.push(command);
        redoStackEmpty.set(false);
    }

    private StandardCommand getFromRedoStack() {
        StandardCommand command = redoStack.pop();

        if (redoStack.size() == 0) {
            redoStackEmpty.set(true);
        }

        return command;
    }

    public BooleanProperty undoStackEmptyProperty() {
        return undoStackEmpty;
    }

    public BooleanProperty redoStackEmptyProperty() {
        return redoStackEmpty;
    }
}
