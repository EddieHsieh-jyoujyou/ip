package logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.Constant;
import logic.command.ByeCommand;
import logic.command.Command;
import logic.command.DeadlineCommand;
import logic.command.DeleteCommand;
import logic.command.DoneCommand;
import logic.command.EventCommand;
import logic.command.FindCommand;
import logic.command.ListCommand;
import logic.command.TodoCommand;
import logic.parser.exceptions.ParseException;

public class DukeParser implements Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     *
     * @param input
     * @return {@code Command}
     * @throws ParseException
     */
    @Override
    public Command parseCommand(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());

        // matches must be called before matcher.group
        if (!matcher.matches()) {
            throw new ParseException(Constant.ERROR_COMMAND);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case ByeCommand.COMMAND_KEYWORD:
            return new ByeCommand();
        case TodoCommand.COMMAND_KEYWORD:
            return new TodoCommandParser().parse(arguments);
        case DeadlineCommand.COMMAND_KEYWORD:
            return new DeadlineCommandParser().parse(arguments);
        case EventCommand.COMMAND_KEYWORD:
            return new EventCommandParser().parse(arguments);
        case ListCommand.COMMAND_KEYWORD:
            return new ListCommand();
        case DeleteCommand.COMMAND_KEYWORD:
            return new DeleteCommandParser().parse(arguments);
        case DoneCommand.COMMAND_KEYWORD:
            return new DoneCommandParser().parse(arguments);
        case FindCommand.COMMAND_KEYWORD:
            return new FindCommandParser().parse(arguments);
        default:
            throw new ParseException(Constant.ERROR_COMMAND);

        }

    }
}
