package logic.parser;

import logic.command.Command;
import logic.parser.exceptions.ParseException;

/**
 * API of parsers.
 * @param <T> is different type of Command.
 * @see logic.command.Command
 */
public interface StorageCommandParser <T extends Command> {
    T parse(Boolean done, String arguments) throws ParseException;
}
