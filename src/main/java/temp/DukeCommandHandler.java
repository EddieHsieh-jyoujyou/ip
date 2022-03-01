package temp;

import commons.Constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Duke CommandHandler is for command filter.
 * By parsing command with regex, any invalid command would fail in this layer.
 * Once command has passed this layer, we could ensure DukeChatBot won't meet strange bug.
 */
public class DukeCommandHandler {
    
    private final ChatBot dukeChatBot;
    
    public DukeCommandHandler() {
        dukeChatBot = new ChatBot();
    }

    /**
     * If command equal to "bye", print goodbye message.
     */
    public void byeHandler() {
        System.out.println(Constant.GOODBYE);
    }

    /**
     * Parse command preliminary and then call DukeChatBot to add this task.
     * @param input command
     */
    public void todoHandler(String input) {
        String taskName = input.replace("todo", "");
        dukeChatBot.addStringToList(taskName, TaskTypeEnum.TODO, "");
    }

    /**
     * Parse command preliminary and then call DukeChatBot to add this task.
     * @param input command
     */
    public void deadlineHandler(String input) {
        String [] task = input.replace("deadline", "").split(" /by ");
        if (task.length != 2) {
            System.out.println(Constant.ERROR_COMMAND);
            return;
        }
        dukeChatBot.addStringToList(task[0], TaskTypeEnum.DEADLINE, task[1]);
    }

    /**
     * Parse command preliminary and then call DukeChatBot to add this task.
     * @param input command
     */
    public void eventHandler(String input) {
        String [] task = input.replace("event ", "").split(" /at ");
        if (task.length != 2) {
            System.out.println(Constant.ERROR_COMMAND);
            return;
        }
        dukeChatBot.addStringToList(task[0], TaskTypeEnum.EVENT, task[1]);
    }

    /**
     * Call DukeChatBot to show the list of task.
     */
    public void listHandler() {
        dukeChatBot.showListOfChatBotContent();
    }

    /**
     * Call DukeChatBot to delete task from task list.
     * @param input command
     */
    public void deleteHandler(String input) {
        Pattern pattern = Pattern.compile("delete [1-9][0-9]*");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            int index = Integer.parseInt(input.split(" ")[1]);
            dukeChatBot.deleteTaskFromListWithIndex(index);
            return;
        }
        System.out.println(Constant.ERROR_COMMAND);
    }

    /**
     * Mark specific task as done.
     * @param input command
     */
    public void doneHandler(String input) {
        Pattern pattern = Pattern.compile("done [1-9][0-9]*");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            Integer taskNumber = Integer.parseInt(input.replace("done ", ""));
            dukeChatBot.markTaskAsDone(taskNumber);
            return;
        }
        System.out.println(Constant.ERROR_COMMAND);
    }

    /**
     * Find task which task name matches keyword.
     * @param input command
     */
    public void findHandler(String input) {
        String taskName = input.replace(Constant.COMMAND_FIND , "");
        dukeChatBot.showListOfChatBotContentWithKeyWord(taskName);
    }

    /**
     * Print unknown error message.
     * @param input command, I want to keep it because this should be recorded by application.
     *              However, we don't have log system in this project yet.
     */
    public void unknownHandler(String input) {
        System.out.println(Constant.ERROR_COMMAND);
    }
}
