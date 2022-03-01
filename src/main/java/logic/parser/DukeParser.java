package logic.parser;

import logic.command.ByeCommand;
import logic.command.Command;
import logic.command.TodoCommand;
import logic.command.DeadlineCommand;
import logic.command.EventCommand;
import logic.command.ListCommand;
import logic.command.DeleteCommand;
import logic.command.DoneCommand;
import logic.command.FindCommand;
import logic.parser.exceptions.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DukeParser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>).*");

    public Command parseCommand(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());

        // matches must be called before matcher.group
        if (!matcher.matches()) {
            throw new ParseException("test");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
            case ByeCommand.COMMAND_KEYWORD:
                return new ByeCommand();
            case TodoCommand.COMMAND_KEYWORD:
                return new TodoCommandParser().parse(arguments);
            case DeadlineCommand.COMMAND_KEYWORD:
                return new DeadlineCommand();
            case EventCommand.COMMAND_KEYWORD:
                return new EventCommand();
            case ListCommand.COMMAND_KEYWORD:
                return new ListCommand();
            case DeleteCommand.COMMAND_KEYWORD:
                return new DeleteCommand();
            case DoneCommand.COMMAND_KEYWORD:
                return new DoneCommand();
            case FindCommand.COMMAND_KEYWORD:
                return new FindCommand();
            default:
                throw new ParseException("Test");
        }

    }
}
