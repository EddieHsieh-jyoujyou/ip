package logic.command;

import commons.Constant;
import model.TaskList;

public class ByeCommand extends Command {

    public static final String COMMAND_KEYWORD = "bye";

    @Override
    public String execute(TaskList list) {
        return Constant.GOODBYE;
    }
}
