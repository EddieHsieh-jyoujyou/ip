import java.util.ArrayList;
import java.util.List;

public class DukeChatBot {
    private List<String> listOfChatBotContent;

    public DukeChatBot() {
        listOfChatBotContent = new ArrayList<>();
    }

    public Boolean addStringToList(String input) {
        return listOfChatBotContent.add(input);
    }

    public void showListOfChatBotContent() {
        System.out.println(Constant.formOutputByListWithLabel(listOfChatBotContent));
    }
}
