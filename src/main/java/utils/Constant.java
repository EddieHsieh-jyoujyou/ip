package utils;

import model.Task;

import java.util.Arrays;
import java.util.List;

public class Constant {
    public final static String COMMAND_BYE = "bye";
    public final static String COMMAND_TODO = "todo";
    public final static String COMMAND_DEADLINE = "deadline";
    public final static String COMMAND_EVENT = "event";
    public final static String COMMAND_LIST = "list";
    public final static String COMMAND_DELETE = "delete";
    public final static String COMMAND_DONE = "done";

    public final static String SINGLE_CHARACTER_TASK_TYPE_TODO = "T";
    public final static String SINGLE_CHARACTER_TASK_TYPE_DEADLINE = "D";
    public final static String SINGLE_CHARACTER_TASK_TYPE_EVENT = "E";

    public final static String TAB = "    ";
    public final static String BLANK = " ";
    public final static String BREAK = "\n";
    public final static String HORIZONTAL_LINE = TAB + "____________________________________________________________\n";

    public final static String GREETINGS = formOutputByList(Arrays
            .asList("Hello! I'm Duke", "What can I do for you?"));

    public final static String GOODBYE = formOutputBySingleString("Bye. Hope to see you again soon!");

    public final static String STRING_SHOW_LIST = "Here are the tasks in your list:";
    public final static String STRING_MARK_AS_DONE = "Nice! I've marked this task as done:";
    public final static String STRING_DELETE_SUCCESS = "Noted. I've removed this task:";

    public final static String ERROR_COMMAND =
            formOutputBySingleString("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    public final static String ERROR_WHILE_MARK_TASK_AS_DONE =
            formOutputBySingleString("Mark task as done failed, index out of bound.");
    public final static String ERROR_WHILE_DELETE_TASK_FROM_LIST =
            formOutputBySingleString("Delete task from list failed.");
    public final static String ERROR_WHILE_LOAD_TASK_LIST_FROM_FILE =
            formOutputBySingleString("Load task list from file failed.");
    public final static String ERROR_WHILE_CREATE_FILE_DIR =
            formOutputBySingleString("Failed to create file");
    public final static String ERROR_WHILE_WRITE_TO_FILE =
            formOutputBySingleString("Failed to write file.");

    /**
     * If the sentence which was printed only has one string, this constructor would form the result string.
     * @param str one String sentence
     * @return result string.
     */
    public static String formOutputBySingleString(String str) {
        return HORIZONTAL_LINE + TAB + BLANK + str + BREAK + HORIZONTAL_LINE;
    }

    /**
     * If there's many sentences need to be printed, this constructor would for the result string.
     * @param list a String Array for sentences need to be printed.
     * @return result string
     */
    public static String formOutputByList(List<String> list) {
        String resultString = HORIZONTAL_LINE;
        for (String str: list) {
            resultString += TAB + BLANK + str + BREAK;
        }
        resultString += HORIZONTAL_LINE;
        return resultString;
    }

    /**
     * Basically is for "list" command in DukeCommandHandler.listHandler -> DukeChatBot.showListOfChatBotContent.
     * Compare to formOutputByList, it would generate task string with label in front of it.
     * @param list a String Array for sentences need to be printed. Actually is DukeChatBot.listOfChatBotContent
     * @return result string
     */
    public static String formOutputByListWithLabel(List<Task> list) {
        String resultString = HORIZONTAL_LINE + TAB + BLANK + STRING_SHOW_LIST + BREAK;
        int count = 1;
        for (Task task: list) {
            resultString += TAB + BLANK + count + "." + task.toOutput() + BREAK;
            count++;
        }
        resultString += HORIZONTAL_LINE;
        return resultString;
    }
}
