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

    private final LocalDate date;
    private final String name;

    /**
     * Constructor.
     *
     * @param name name of task
     * @param date date of task
     */
    public DeadlineCommand(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public void execute(TaskList list) throws TaskException, ParseException {
        Task task = new Task(name, TaskTypeEnum.DEADLINE, date);
        list.add(task);
        OutputInterface.writer(task.toOutput(), list.getTaskList().size());
    }
}
