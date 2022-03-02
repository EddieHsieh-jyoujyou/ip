package logic.command;

import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;
import model.TaskTypeEnum;
import model.exceptions.TaskException;

public class TodoCommand extends Command {
    public static final String COMMAND_KEYWORD = "todo";

    private final String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList list) throws TaskException, ParseException {
        Task task = new Task(name, TaskTypeEnum.TODO, null);
        list.add(task);
        OutputInterface.writer(task.toOutput(), list.getTaskList().size());
    }
}
