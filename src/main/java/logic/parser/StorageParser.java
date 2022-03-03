package logic.parser;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.Constant;
import logic.command.Command;
import logic.command.DeadlineCommand;
import logic.command.EventCommand;
import logic.command.TodoCommand;
import logic.parser.exceptions.ParseException;

public class StorageParser implements Parser {
    private static final Pattern STORAGE_COMMAND_FORMAT =
            Pattern.compile("(?<commandSingleWord>\\S+) \\| (?<done>[0-1]) \\| (?<arguments>.*)");

    /**
     * Parser for storage to analyze input string.
     * @param input line of string read from duke.txt
     * @return different type of command, todo, event, deadline
     * @throws ParseException exception for parsing error
     */
    @Override
    public Command parseCommand(String input) throws ParseException {
        final Matcher matcher = STORAGE_COMMAND_FORMAT.matcher(input.trim());

        if (!matcher.matches()) {
            throw new ParseException(Constant.ERROR_COMMAND);
        }

        final String commandSingleWord = matcher.group("commandSingleWord");
        final Boolean done = !Objects.equals(matcher.group("done"), "0");
        final String arguments = matcher.group("arguments");

        switch (commandSingleWord) {

        case TodoCommand.COMMAND_SINGLE_KEYWORD:
            return new TodoCommandParser().parse(done, arguments);
        case DeadlineCommand.COMMAND_SINGLE_KEYWORD:
            return new DeadlineCommandParser().parse(done, arguments);
        case EventCommand.COMMAND_SINGLE_KEYWORD:
            return new EventCommandParser().parse(done, arguments);
        default:
            throw new ParseException(Constant.ERROR_COMMAND);

        }
    }
}
