import java.io.IOException;

import commons.Constant;
import commons.util.io.InputInterface;
import commons.util.io.OutputInterface;
import logic.command.ByeCommand;
import logic.command.Command;
import logic.command.DeadlineCommand;
import logic.command.DeleteCommand;
import logic.command.DoneCommand;
import logic.command.EventCommand;
import logic.command.TodoCommand;
import logic.parser.DukeParser;
import logic.parser.exceptions.ParseException;
import model.DukeTaskList;
import model.TaskList;
import model.exceptions.TaskException;
import storage.DukeStorage;
import storage.Storage;

/**
 * Main Logic Bot of the Duke App.
 */
public class DukeChatBot implements Bot {
    private Boolean isActive;
    private final DukeParser dukeParser;
    private final TaskList list;
    private Storage storage;

    /**
     * Constructs a {@code DukeChatBot}.
     */
    protected DukeChatBot() {
        this.isActive = false;
        this.dukeParser = new DukeParser();
        this.list = new DukeTaskList();


    }

    @Override
    public void powerOn() {
        this.isActive = true;

        // Initialize storage
        try {
            this.storage = new DukeStorage();
        } catch (IOException e) {
            OutputInterface.writer(Constant.ERROR_WHILE_CREATE_FILE);
            powerOff();
        }
        list.setTaskList(storage.read().getTaskList());

        greeting();
        start();
    }

    @Override
    public void powerOff() {
        this.isActive = false;
    }

    private void start() {
        while (isActive) {
            try {
                Command command = dukeParser.parseCommand(InputInterface.reader());
                command.execute(list);

                if (command instanceof ByeCommand) {
                    powerOff();
                }
                if (command instanceof DeadlineCommand
                        || command instanceof DeleteCommand
                        || command instanceof DoneCommand
                        || command instanceof EventCommand
                        || command instanceof TodoCommand
                ) {
                    storage.save(list);
                }

            } catch (ParseException | TaskException e) {
                OutputInterface.writer(Constant.formOutputBySingleString(e.getMessage()));
            }
        }

    }

    private void greeting() {
        OutputInterface.writer(Constant.GREETINGS);
    }
}
