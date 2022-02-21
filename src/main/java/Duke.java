import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Constant.GREETINGS);

        Scanner scanner = new Scanner(System.in);
        DukeChatBot chatBot = new DukeChatBot();

        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (Objects.equals(input, Constant.CONDITION_BYE)) {
                break;
            }
            switch (input) {
                case(Constant.CONDITION_BYE):
                    break;
                case(Constant.CONDITION_LIST):
                    chatBot.showListOfChatBotContent();
                    continue;
            }
            Pattern pattern = Pattern.compile("done [1-9][0-9]*");
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                Integer taskNumber = Integer.parseInt(input.replace("done ", ""));
                chatBot.markTaskAsDone(taskNumber);
                continue;
            }

            if (input.startsWith("todo ")) {
                chatBot.addStringToList(input.replace("todo ", ""), "T", "");
            } else if (input.startsWith("deadline ") && input.contains(" /by ")) {
                String [] task = input.replace("deadline ", "").split(" /by ");
                if (task.length != 2) {
                    System.out.println(Constant.ERROR_WHILE_ADD_TASK);
                    continue;
                }
                chatBot.addStringToList(task[0], "D", task[1]);
            } else if (input.startsWith("event ") && input.contains(" /at ")) {
                String [] task = input.replace("event ", "").split(" /at ");
                if (task.length != 2) {
                    System.out.println(Constant.ERROR_WHILE_ADD_TASK);
                    continue;
                }
                chatBot.addStringToList(task[0], "E", task[1]);
            } else {
                System.out.println(Constant.ERROR_WHILE_ADD_TASK);
            }
        }

        System.out.println(Constant.GOODBYE);
    }

}
