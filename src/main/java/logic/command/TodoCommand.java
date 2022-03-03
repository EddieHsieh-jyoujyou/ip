package logic.command;

import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;
import model.TaskTypeEnum;
import model.exceptions.TaskException;

public class TodoCommand extends Command {
    public static final String COMMAND_KEYWORD = "todo";
    public static final String COMMAND_SINGLE_KEYWORD = "T";

    private final String name;
    private final Boolean done;

    /**
     * Constructor.
     *
     * @param name name of task
     */
    public TodoCommand(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Constructor with done param.
     * @param name name of task
     * @param done whether task has been done
     */
    public TodoCommand(String name, Boolean done) {
        this.name = name;
        this.done = done;
    }

    @Override
    public String execute(TaskList list) throws TaskException, ParseException {
        Task task = new Task(name, TaskTypeEnum.TODO, null);
        if (done) {
            task.markTaskAsDone();
        }
        list.add(task);
        return OutputInterface.formatOutputString(task.toOutput(), list.getTaskList().size());
    }
}
