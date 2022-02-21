import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Constant.GREETINGS);

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()){
            String input = scanner.nextLine();
            if (Objects.equals(input, Constant.CONDITION_BYE)) {
                break;
            }

            System.out.println(Constant.formOutputBySingleString(input));

        }

        System.out.println(Constant.GOODBYE);
    }

}
