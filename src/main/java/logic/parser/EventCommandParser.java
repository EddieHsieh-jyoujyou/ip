package logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.util.parser.ParseUtil;
import logic.command.EventCommand;
import logic.parser.exceptions.ParseException;

public class EventCommandParser implements CommandParser<EventCommand>, StorageCommandParser<EventCommand> {
    private static final Pattern EVENT_COMMAND_FORMAT = Pattern.compile("(?<task>.*)( /at )(?<date>.*)");
    private static final Pattern STORAGE_EVENT_COMMAND_FORMAT = Pattern.compile("(?<task>.*)( \\| )(?<date>.*)");

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

    @Override
    public EventCommand parse(Boolean done, String arguments) throws ParseException {
        final Matcher matcher = STORAGE_EVENT_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException("Error occur in EventCommandParser");
        }

        final String taskName = matcher.group("task");
        final String date = matcher.group("date");

        return new EventCommand(taskName, ParseUtil.parseStringToLocalDate(date), done);
    }
}
