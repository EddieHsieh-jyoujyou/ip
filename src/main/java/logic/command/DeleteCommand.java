package logic.command;

import commons.Constant;
import commons.util.io.OutputInterface;
import model.Model;

public class DeleteCommand extends Command {
    public static final String COMMAND_KEYWORD = "delete";

    @Override
    public void execute(Model model) {
        OutputInterface.writer(Constant.GOODBYE);
    }
}
