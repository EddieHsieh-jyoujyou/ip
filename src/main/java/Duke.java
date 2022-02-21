import java.util.Objects;
import java.util.Scanner;

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
            if (chatBot.addStringToList(input)) {
                System.out.println(Constant.formOutputBySingleString("added: " + input));
            }
        }

        System.out.println(Constant.GOODBYE);
    }

}
