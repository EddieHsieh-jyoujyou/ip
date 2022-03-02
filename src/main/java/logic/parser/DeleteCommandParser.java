package logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.command.DeleteCommand;
import logic.parser.exceptions.ParseException;

public class DeleteCommandParser implements Parser<DeleteCommand> {
    private static final Pattern DELETE_COMMAND_FORMAT = Pattern.compile("(?<index>[0-9]+)");

    @Override
    public DeleteCommand parse(String arguments) throws ParseException {
        final Matcher matcher = DELETE_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException("Error occur in DeleteCommandParser");
        }

        final int index = Integer.parseInt(matcher.group("index"));

        return new DeleteCommand(index);
    }
}
