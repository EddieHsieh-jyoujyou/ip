package logic.command;

import java.time.LocalDate;

import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;
import model.TaskTypeEnum;
import model.exceptions.TaskException;

public class DeadlineCommand extends Command {
    private final LocalDate date;
    private final String name;

    public static final String COMMAND_KEYWORD = "deadline";

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
