import utils.Constant;

import java.util.Objects;
import java.util.Scanner;

/**
 * Entry point of Duke project.
 * This class provide user to type command, by identifying different prefix of command, call different method of
 * DukeCommandHandler.
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println(Constant.GREETINGS);

        Scanner scanner = new Scanner(System.in);
        DukeCommandHandler handler = new DukeCommandHandler();

        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (Objects.equals(input, Constant.COMMAND_BYE)) {
                handler.byeHandler();
                break;
            } else if (input.startsWith(Constant.COMMAND_TODO)) {
                handler.todoHandler(input);
            } else if (input.startsWith(Constant.COMMAND_DEADLINE)) {
                handler.deadlineHandler(input);
            } else if (input.startsWith(Constant.COMMAND_EVENT)) {
                handler.eventHandler(input);
            } else if (Objects.equals(input, Constant.COMMAND_LIST)) {
                handler.listHandler();
            } else if (input.startsWith(Constant.COMMAND_DELETE)) {
                handler.deleteHandler(input);
            } else if (input.startsWith(Constant.COMMAND_DONE)) {
                handler.doneHandler(input);
            } else {
                handler.unknownHandler(input);
            }

        }

    }

}
