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

    public static void writer(String taskOutput, int listSize) {

        System.out.println(
                Constant.formOutputByList(Arrays
                .asList("Got it. I've added this task:",
                        "  " + taskOutput,
                        "Now you have " + listSize + " tasks in the list.")));
    }
}
