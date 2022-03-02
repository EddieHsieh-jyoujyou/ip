package logic.command;

import commons.Constant;
import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_KEYWORD = "list";

    @Override
    public void execute(TaskList list) throws ParseException {
        OutputInterface.writer(Constant.formOutputByListWithLabel(list.getTaskList(),
                Constant.STRING_SHOW_LIST));
    }
}
