package logic.command;

import commons.Constant;
import commons.util.io.OutputInterface;
import model.TaskList;

public class ByeCommand extends Command {

    public static final String COMMAND_KEYWORD = "bye";

    @Override
    public void execute(TaskList list) {
        OutputInterface.writer(Constant.GOODBYE);
    }
}
