package logic.command;

import java.util.Arrays;

import commons.Constant;
import commons.util.io.OutputInterface;
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
    public void execute(TaskList list) throws ParseException {
        Task task = list.get(index - 1);
        if (task == null) {
            OutputInterface.writer(Constant.ERROR_WHILE_MARK_TASK_AS_DONE);
            return;
        }
        task.markTaskAsDone();
        OutputInterface.writer(Constant.formOutputByList(Arrays
                .asList(Constant.STRING_MARK_AS_DONE, "  " + task.toOutput())));
    }
}
