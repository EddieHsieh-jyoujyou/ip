package commons;

import java.util.Arrays;
import java.util.List;

import logic.parser.exceptions.ParseException;
import model.Task;

public class Constant {

    public static final String SINGLE_CHARACTER_TASK_TYPE_TODO = "T";
    public static final String SINGLE_CHARACTER_TASK_TYPE_DEADLINE = "D";
    public static final String SINGLE_CHARACTER_TASK_TYPE_EVENT = "E";

    public static final String TAB = "    ";
    public static final String BLANK = " ";
    public static final String BREAK = "\n";
    public static final String HORIZONTAL_LINE = TAB + "____________________________________________________________\n";

    public static final String GREETINGS = formOutputByList(Arrays
            .asList("Hello! I'm Duke", "What can I do for you?"));
    public static final String GOODBYE = formOutputBySingleString("Bye. Hope to see you again soon!");

    public static final String STRING_SHOW_LIST = "Here are the tasks in your list:";
    public static final String STRING_SHOW_MATCHED_LIST = "Here are the matching tasks in your list:";
    public static final String STRING_MARK_AS_DONE = "Nice! I've marked this task as done:";
    public static final String STRING_DELETE_SUCCESS = "Noted. I've removed this task:";

    public static final String STRING_ERROR_EMPTY_TASK_NAME = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String STRING_ERROR_INVALID_TIME_FORMAT = "Time format invalid. Format should be yyyy-MM-dd.";
    public static final String STRING_ERROR_RUNTIME_FAILURE_PARSING_DATE = "Runtime exception while parsing date.";

    public static final String ERROR_COMMAND =
            formOutputBySingleString("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    public static final String ERROR_WHILE_MARK_TASK_AS_DONE =
            formOutputBySingleString("Mark task as done failed, index out of bound.");
    public static final String ERROR_WHILE_DELETE_TASK_FROM_LIST =
            formOutputBySingleString("Delete task from list failed.");
    public static final String ERROR_WHILE_LOAD_TASK_LIST_FROM_FILE =
            formOutputBySingleString("Load task list from file failed.");
    public static final String ERROR_WHILE_CREATE_FILE_DIR =
            formOutputBySingleString("Failed to create file directory.");
    public static final String ERROR_WHILE_CREATE_FILE =
            formOutputBySingleString("Failed to create file");
    public static final String ERROR_WHILE_WRITE_TO_FILE =
            formOutputBySingleString("Failed to write file.");
    public static final String ERROR_WHILE_FIND_TASK_WITH_EMPTY_KEYWORD =
            formOutputBySingleString("Keyword could not be empty.");

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
     * Basically is for "list" command in temp.DukeCommandHandler.listHandler -> DukeChatBot.showListOfChatBotContent.
     * Compare to formOutputByList, it would generate task string with label in front of it.
     * @param list a String Array for sentences need to be printed. Actually is DukeChatBot.listOfChatBotContent
     * @return result string
     */
    public static String formOutputByListWithLabel(List<Task> list, String description) throws ParseException {
        String resultString = HORIZONTAL_LINE + TAB + BLANK + description + BREAK;
        int count = 1;
        for (Task task: list) {
            resultString += TAB + BLANK + count + "." + task.toOutput() + BREAK;
            count++;
        }
        resultString += HORIZONTAL_LINE;
        return resultString;
    }
}
