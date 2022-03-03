package logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.command.DoneCommand;
import logic.parser.exceptions.ParseException;

public class DoneCommandParser implements CommandParser<DoneCommand> {
    private static final Pattern DONE_COMMAND_FORMAT = Pattern.compile("(?<index>[0-9]+)");

    @Override
    public DoneCommand parse(String arguments) throws ParseException {
        final Matcher matcher = DONE_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException("Error occur in DoneCommandParser");
        }

        final int index = Integer.parseInt(matcher.group("index"));

        return new DoneCommand(index);
    }
}
