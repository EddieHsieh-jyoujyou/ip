package logic.command;

import java.time.LocalDate;

import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;
import model.TaskTypeEnum;
import model.exceptions.TaskException;

public class DeadlineCommand extends Command {
    public static final String COMMAND_KEYWORD = "deadline";
    public static final String COMMAND_SINGLE_KEYWORD = "D";

    private final LocalDate date;
    private final String name;
    private final Boolean done;

    /**
     * Constructor.
     *
     * @param name name of task
     * @param date date of task
     */
    public DeadlineCommand(String name, LocalDate date) {
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
    public DeadlineCommand(String name, LocalDate date, Boolean done) {
        this.name = name;
        this.date = date;
        this.done = done;
    }

    @Override
    public String execute(TaskList list) throws TaskException, ParseException {
        Task task = new Task(name, TaskTypeEnum.DEADLINE, date);
        if (done) {
            task.markTaskAsDone();
        }
        list.add(task);
        return OutputInterface.formatOutputString(task.toOutput(), list.getTaskList().size());
    }
}
