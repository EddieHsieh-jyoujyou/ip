package logic.command;

import commons.Constant;
import commons.util.io.OutputInterface;
import model.Model;

public class DeadlineCommand extends Command {

    public static final String COMMAND_KEYWORD = "deadline";

    public DeadlineCommand() {

    }

    @Override
    public void execute(Model model) {
        OutputInterface.writer(Constant.GOODBYE);
    }
}
