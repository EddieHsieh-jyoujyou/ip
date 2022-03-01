package logic.command;

import model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Execute the command.
     *
     * @param model {@code Model} which the command should operate on.
     */
    public abstract void execute(Model model);
}
