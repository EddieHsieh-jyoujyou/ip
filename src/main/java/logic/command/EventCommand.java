package logic.command;

import commons.Constant;
import commons.util.io.OutputInterface;
import model.Model;

public class EventCommand extends Command {
    public static final String COMMAND_KEYWORD = "event";

    @Override
    public void execute(Model model) {
        OutputInterface.writer(Constant.GOODBYE);
    }
}
