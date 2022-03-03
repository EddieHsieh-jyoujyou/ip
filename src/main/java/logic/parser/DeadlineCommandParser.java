package logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commons.util.parser.ParseUtil;
import logic.command.DeadlineCommand;
import logic.parser.exceptions.ParseException;

public class DeadlineCommandParser implements CommandParser<DeadlineCommand>, StorageCommandParser<DeadlineCommand> {
    private static final Pattern DEADLINE_COMMAND_FORMAT = Pattern.compile("(?<task>.*)( /by )(?<date>.*)");
    private static final Pattern STORAGE_DEADLINE_COMMAND_FORMAT = Pattern.compile("(?<task>.*)( \\| )(?<date>.*)");

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

    @Override
    public DeadlineCommand parse(Boolean done, String arguments) throws ParseException {
        final Matcher matcher = STORAGE_DEADLINE_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException("Error occur in DeadlineCommandParser");
        }

        final String taskName = matcher.group("task");
        final String date = matcher.group("date");

        return new DeadlineCommand(taskName, ParseUtil.parseStringToLocalDate(date), done);
    }
}
