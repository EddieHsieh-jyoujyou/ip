import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DukeChatBot {
    private final List<Task> listOfChatBotContent;

    public DukeChatBot() {
        listOfChatBotContent = new ArrayList<>();
    }

    public void addStringToList(String input, TaskTypeEnum type, String specificTime) {
        String taskName;
        if (input.startsWith(" ")) {
            taskName = input.replaceFirst(" ", "");
        } else {
            taskName = input;
        }

        Task task;
        try {
            task = new Task(taskName, type, specificTime);
            listOfChatBotContent.add(task);
        } catch (RuntimeException e) {
            System.out.println(Constant.formOutputBySingleString(e.getMessage()));
            return;
        }

        System.out.println(Constant.formOutputByList(Arrays
                .asList("Got it. I've added this task:",
                        "  " + task.toOutput(),
                        "Now you have " + listOfChatBotContent.size() + " tasks in the list.")));
    }

    public void deleteTaskFromListWithIndex(int index) {
        if (index > listOfChatBotContent.size()) {
            System.out.println(Constant.ERROR_WHILE_DELETE_TASK_FROM_LIST);
            return;
        }
        Task task = listOfChatBotContent.remove(index - 1);
        System.out.println(Constant.formOutputByList(Arrays
                .asList(Constant.STRING_DELETE_SUCCESS,
                        "  " + task.toOutput(),
                        "Now you have " + listOfChatBotContent.size() + " tasks in the list.")));
    }

    public void markTaskAsDone(Integer index) {
        if (index > listOfChatBotContent.size()) {
            System.out.println(Constant.ERROR_WHILE_MARK_TASK_AS_DONE);
            return;
        }
        Task task = listOfChatBotContent.get(index - 1);
        task.markTaskAsDone();
        System.out.println(Constant.formOutputByList(Arrays
                .asList(Constant.STRING_MARK_AS_DONE, "  " + task.toOutput())));
    }

    public void showListOfChatBotContent() {
        System.out.println(Constant.formOutputByListWithLabel(listOfChatBotContent));
    }
}
