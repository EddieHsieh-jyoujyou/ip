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

            Pattern pattern = Pattern.compile("done [1-9][0-9]*");
            Matcher matcher = pattern.matcher(input);

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
            if (matcher.matches()) {
                Integer taskNumber = Integer.parseInt(input.replace("done ", ""));
                chatBot.markTaskAsDone(taskNumber);
                continue;
            }
            chatBot.addStringToList(input);
        }

        System.out.println(Constant.GOODBYE);
    }

}
