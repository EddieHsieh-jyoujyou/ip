package logic.command;

import commons.Constant;
import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;

public class DeleteCommand extends Command {
    private final int index;
    public static final String COMMAND_KEYWORD = "delete";

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list) throws ParseException {
        Task task = list.remove(index - 1);
        if (task == null) {
            OutputInterface.writer(Constant.ERROR_WHILE_DELETE_TASK_FROM_LIST);
            return;
        }
        OutputInterface.writer(task.toOutput(), list.getTaskList().size());
    }
}
