import java.util.Arrays;
import java.util.List;

public class Constant {
    public final static String TAB = "    ";
    public final static String BLANK = " ";
    public final static String BREAK = "\n";
    public final static String HORIZONTAL_LINE = TAB + "____________________________________________________________\n";

    public final static String GREETINGS = formOutputByList(Arrays
            .asList("Hello! I'm Duke", "What can I do for you?"));

    public final static String GOODBYE = formOutputBySingleString("Bye. Hope to see you again soon!");

    public final static String STRING_SHOW_LIST = "Here are the tasks in your list:";
    public final static String STRING_MARK_AS_DONE = "Nice! I've marked this task as done:";

    public final static String CONDITION_BYE = "bye";
    public final static String CONDITION_LIST = "list";

    public final static String ERROR_WHILE_ADD_TASK = formOutputBySingleString("Adding task failed.");
    public final static String ERROR_WHILE_MARK_TASK_AS_DONE =
            formOutputBySingleString("Mark task as done failed, index out of bound.");

    public static String formOutputBySingleString(String str) {
        return HORIZONTAL_LINE + TAB + BLANK + str + BREAK + HORIZONTAL_LINE;
    }

    public static String formOutputByList(List<String> list) {
        String resultString = HORIZONTAL_LINE;
        for (String str: list) {
            resultString += TAB + BLANK + str + BREAK;
        }
        resultString += HORIZONTAL_LINE;
        return resultString;
    }

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
