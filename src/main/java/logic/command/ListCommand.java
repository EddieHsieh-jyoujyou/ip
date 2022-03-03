package logic.command;

import commons.Constant;
import logic.parser.exceptions.ParseException;
import model.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_KEYWORD = "list";

    @Override
    public String execute(TaskList list) throws ParseException {
        return Constant.formOutputByListWithLabel(list.getTaskList(), Constant.STRING_SHOW_LIST);
    }
}
