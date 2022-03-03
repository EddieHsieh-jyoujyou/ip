package logic.command;

import java.time.LocalDate;

import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;
import model.TaskTypeEnum;
import model.exceptions.TaskException;

public class EventCommand extends Command {
    public static final String COMMAND_KEYWORD = "event";
    public static final String COMMAND_SINGLE_KEYWORD = "E";

    private final String name;
    private final LocalDate date;
    private final Boolean done;

    /**
     * Constructor.
     *
     * @param name name of task
     * @param date date of task
     */
    public EventCommand(String name, LocalDate date) {
        this.name = name;
        this.date = date;
        this.done = false;
    }

    /**
     * Constructor with done param.
     * @param name name of task
     * @param date date of task
     * @param done whether task has been done
     */
    public EventCommand(String name, LocalDate date, Boolean done) {
        this.name = name;
        this.date = date;
        this.done = done;
    }

    @Override
    public String execute(TaskList list) throws TaskException, ParseException {
        Task task = new Task(name, TaskTypeEnum.EVENT, date);
        if (done) {
            task.markTaskAsDone();
        }
        list.add(task);
        return OutputInterface.formatOutputString(task.toOutput(), list.getTaskList().size());
    }
}
