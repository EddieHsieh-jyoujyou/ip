package logic.command;

import logic.parser.exceptions.ParseException;
import model.TaskList;
import model.exceptions.TaskException;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Execute the command.
     *
     * @param list {@code TaskList} which the command should operate on.
     */
    public abstract void execute(TaskList list) throws TaskException, ParseException;
}
