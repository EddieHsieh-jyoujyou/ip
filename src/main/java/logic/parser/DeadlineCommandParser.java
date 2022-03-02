package logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.util.parser.ParseUtil;
import logic.command.DeadlineCommand;
import logic.parser.exceptions.ParseException;

public class DeadlineCommandParser implements Parser<DeadlineCommand> {
    private static final Pattern DEADLINE_COMMAND_FORMAT = Pattern.compile("(?<task>.*)( /by )(?<date>.*)");

    @Override
    public DeadlineCommand parse(String arguments) throws ParseException {
        final Matcher matcher = DEADLINE_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException("Error occur in DeadlineCommandParser");
        }

        final String taskName = matcher.group("task");
        final String date = matcher.group("date");

        return new DeadlineCommand(taskName, ParseUtil.parseStringToLocalDate(date));
    }
}
