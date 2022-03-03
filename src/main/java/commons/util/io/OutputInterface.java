package commons.util.io;

import java.util.Arrays;

import commons.Constant;

/**
 * Used to handle output in Duke App.
 */
public class OutputInterface {
    /**
     * Simple writer.
     */
    public static void writer(String str) {
        System.out.println(str);
    }

    /**
     * Form output string with special format.
     * @param taskOutput task.output()
     * @param listSize size of list
     * @return string
     */
    public static String formatOutputString(String taskOutput, int listSize) {
        return Constant.formOutputByList(Arrays
                .asList("Got it. I've added this task:",
                        "  " + taskOutput,
                        "Now you have " + listSize + " tasks in the list."));
    }
}
