package logic.command;

import java.util.Arrays;

import commons.Constant;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;

public class DoneCommand extends Command {
    public static final String COMMAND_KEYWORD = "done";

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list) throws ParseException {
        Task task = list.get(index - 1);
        if (task == null) {
            return Constant.ERROR_WHILE_MARK_TASK_AS_DONE;
        }
        task.markTaskAsDone();
        return Constant.formOutputByList(Arrays
                .asList(Constant.STRING_MARK_AS_DONE, "  " + task.toOutput()));
    }
}
