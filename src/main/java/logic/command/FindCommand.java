package logic.command;

import java.util.ArrayList;
import java.util.List;

import commons.Constant;
import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public static final String COMMAND_KEYWORD = "find";

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList list) throws ParseException {
        List<Task> newTaskList = new ArrayList<>();
        for (Task task: list.getTaskList()) {
            if (task.getTaskName().contains(keyword)) {
                newTaskList.add(task);
            }
        }
        OutputInterface.writer(Constant.formOutputByListWithLabel(newTaskList,
                Constant.STRING_SHOW_MATCHED_LIST));
    }
}
