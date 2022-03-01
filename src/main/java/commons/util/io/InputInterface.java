package commons.util.io;

import java.util.Scanner;

public class InputInterface {
    public static String reader() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
