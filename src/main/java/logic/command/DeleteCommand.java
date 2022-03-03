package logic.command;

import commons.Constant;
import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_KEYWORD = "delete";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list) throws ParseException {
        Task task = list.remove(index - 1);
        if (task == null) {
            return Constant.ERROR_WHILE_DELETE_TASK_FROM_LIST;
        }
        return OutputInterface.formatOutputString(task.toOutput(), list.getTaskList().size());
    }
}
