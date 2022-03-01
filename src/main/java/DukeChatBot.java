import commons.Constant;
import commons.util.io.InputInterface;
import commons.util.io.OutputInterface;
import logic.command.ByeCommand;
import logic.command.Command;
import logic.parser.DukeParser;
import logic.parser.exceptions.ParseException;
import model.DukeModel;
import model.Model;

/**
 * Main Logic Bot of the Duke App.
 */
public class DukeChatBot implements Bot {
    private Boolean isActive;
    private final DukeParser dukeParser;
    private final Model model;

    /**
     * Constructs a {@code DukeChatBot}.
     */
    public DukeChatBot() {
        this.isActive = false;
        this.dukeParser = new DukeParser();
        this.model = new DukeModel();
    }

    @Override
    public void powerOn() {
        this.isActive = true;
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
                command.execute(model);

                if (command instanceof ByeCommand) {
                    powerOff();
                }

            } catch (ParseException e) {
                OutputInterface.writer(Constant.ERROR_COMMAND);
            }
        }

    }
}
