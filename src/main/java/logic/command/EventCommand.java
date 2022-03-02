package logic.command;

import java.time.LocalDate;

import commons.Constant;
import commons.util.io.OutputInterface;
import logic.parser.exceptions.ParseException;
import model.Task;
import model.TaskList;
import model.TaskTypeEnum;
import model.exceptions.TaskException;

public class EventCommand extends Command {
    private final String name;
    private final LocalDate date;

    public static final String COMMAND_KEYWORD = "event";

    public EventCommand(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public void execute(TaskList list) throws TaskException, ParseException {
        Task task = new Task(name, TaskTypeEnum.EVENT, date);
        list.add(task);
        OutputInterface.writer(task.toOutput(), list.getTaskList().size());
    }
}
