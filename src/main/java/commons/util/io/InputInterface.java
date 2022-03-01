package commons.util.io;

import java.util.Scanner;

/**
 * Used to handle input in Duke App.
 */
public class InputInterface {
    /**
     * Could use InputInterface.reader() to get input string.
     *
     * @return a string of input, which would be parsed to command later.
     */
    public static String reader() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
