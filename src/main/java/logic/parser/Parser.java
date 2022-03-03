package logic.parser;

import logic.command.Command;
import logic.parser.exceptions.ParseException;

/**
 * API of parsers.
 */
public interface Parser {
    Command parseCommand(String input) throws ParseException;
}
