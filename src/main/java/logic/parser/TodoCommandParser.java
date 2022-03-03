package logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.command.TodoCommand;
import logic.parser.exceptions.ParseException;

public class TodoCommandParser implements CommandParser<TodoCommand>, StorageCommandParser<TodoCommand> {
    private static final Pattern TODO_COMMAND_FORMAT = Pattern.compile("(?<task>.*)");
    private static final Pattern DEADLINE_TODO_COMMAND_FORMAT = Pattern.compile("(?<task>.*)");

    @Override
    public TodoCommand parse(String arguments) throws ParseException {
        final Matcher matcher = TODO_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException("Error occur in TodoCommandParser");
        }

        String taskName = matcher.group("task");

        return new TodoCommand(taskName);
    }

    @Override
    public TodoCommand parse(Boolean done, String arguments) throws ParseException {
        final Matcher matcher = DEADLINE_TODO_COMMAND_FORMAT.matcher(arguments.trim());

        if (!matcher.matches()) {
            throw new ParseException("Error occur in TodoCommandParser");
        }

        String taskName = matcher.group("task");

        return new TodoCommand(taskName, done);
    }
}
