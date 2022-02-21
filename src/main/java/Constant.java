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

    public final static String ADD = " added: ";

    public final static String CONDITION_BYE = "bye";
    public final static String CONDITION_LIST = "list";

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

    public static String formOutputByListWithLabel(List<String> list) {
        String resultString = HORIZONTAL_LINE;
        int count = 1;
        for (String str: list) {
            resultString += TAB + BLANK + count + ". " + str + BREAK;
            count++;
        }
        resultString += HORIZONTAL_LINE;
        return resultString;
    }
}
