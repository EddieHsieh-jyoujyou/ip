import model.TaskTypeEnum;
import utils.Constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Duke CommandHandler is for command filter.
 * By parsing command with regex, any invalid command would fail in this layer.
 * Once command has passed this layer, we could ensure DukeChatBot won't meet strange bug.
 */
public class DukeCommandHandler {
    
    private final DukeChatBot dukeChatBot;
    
    public DukeCommandHandler() {
        dukeChatBot = new DukeChatBot();
    }
    
    public void byeHandler() {
        System.out.println(Constant.GOODBYE);
    }
    
    public void todoHandler(String input) {
        String taskName = input.replace("todo", "");
        dukeChatBot.addStringToList(taskName, TaskTypeEnum.TODO, "");
    }
    
    public void deadlineHandler(String input) {
        String [] task = input.replace("deadline ", "").split(" /by ");
        if (task.length != 2) {
            System.out.println(Constant.ERROR_COMMAND);
            return;
        }
        dukeChatBot.addStringToList(task[0], TaskTypeEnum.DEADLINE, task[1]);
    }
    
    public void eventHandler(String input) {
        String [] task = input.replace("event ", "").split(" /at ");
        if (task.length != 2) {
            System.out.println(Constant.ERROR_COMMAND);
            return;
        }
        dukeChatBot.addStringToList(task[0], TaskTypeEnum.EVENT, task[1]);
    }
    
    public void listHandler() {
        dukeChatBot.showListOfChatBotContent();
    }
    
    public void deleteHandler(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        dukeChatBot.deleteTaskFromListWithIndex(index);
    }
    
    public void doneHandler(String input) {
        Pattern pattern = Pattern.compile("done [1-9][0-9]*");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            Integer taskNumber = Integer.parseInt(input.replace("done ", ""));
            dukeChatBot.markTaskAsDone(taskNumber);
        }
    }
    
    public void unknownHandler(String input) {
        System.out.println(Constant.ERROR_COMMAND);
    }
}
