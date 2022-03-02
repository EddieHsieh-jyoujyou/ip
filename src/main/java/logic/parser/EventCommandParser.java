package logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.util.parser.ParseUtil;
import logic.command.EventCommand;
import logic.parser.exceptions.ParseException;

public class EventCommandParser implements Parser<EventCommand> {
    private final static Pattern EVENT_COMMAND_FORMAT = Pattern.compile("(?<task>.*)( /at )(?<date>.*)");

    @Override
    public EventCommand parse(String arguments) throws ParseException {
        final Matcher matcher = EVENT_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException("Error occur in EventCommandParser");
        }

        final String taskName = matcher.group("task");
        final String date = matcher.group("date");

        return new EventCommand(taskName, ParseUtil.parseStringToLocalDate(date));
    }
}
