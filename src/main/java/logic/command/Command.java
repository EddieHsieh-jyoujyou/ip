package logic.command;

import model.Model;

public abstract class Command {
    public abstract void execute(Model model);
}
