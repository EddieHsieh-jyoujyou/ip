package logic.parser;

import logic.command.DeadlineCommand;

public class DeadlineCommandParser implements Parser {

    @Override
    public DeadlineCommand parse(String arguments) {
        return new DeadlineCommand();
    }
}
