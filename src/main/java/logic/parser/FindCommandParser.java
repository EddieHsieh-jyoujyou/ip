package logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.command.FindCommand;
import logic.parser.exceptions.ParseException;

public class FindCommandParser implements Parser<FindCommand> {
    private static final Pattern FIND_COMMAND_FORMAT = Pattern.compile("(?<keyword>.*)");

    @Override
    public FindCommand parse(String arguments) throws ParseException {
        final Matcher matcher = FIND_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException("Error occur in FindCommand");
        }

        final String keyword = matcher.group("keyword");

        return new FindCommand(keyword);
    }
}
