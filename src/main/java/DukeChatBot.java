import model.Task;
import model.TaskException;
import model.TaskTypeEnum;
import utils.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DukeChatBot {
    private final List<Task> listOfChatBotContent;
    private Path dukeChatRecordPath;

    private void loadTaskList() {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        dukeChatRecordPath = Paths.get(root.toString(), "data", "duke.txt");

        if (createFileDirectoryIfNotExist(dukeChatRecordPath)) {
            loadTaskListFromFile(dukeChatRecordPath);
        }
    }

    private void loadTaskListFromFile(Path path) {
        File dukeChatRecord = new File(path.toString());
        try {
            Scanner scanner = new Scanner(dukeChatRecord);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String [] taskString = input.split(" \\| ");
                if (Objects.equals(taskString[0], Constant.SINGLE_CHARACTER_TASK_TYPE_TODO)) {
                    try {
                        Task task = new Task(taskString[2], TaskTypeEnum.TODO, "");
                        if (Objects.equals(taskString[1], "1")) {
                            task.markTaskAsDone();
                        }
                        listOfChatBotContent.add(task);
                    } catch (TaskException e) {
                        e.printStackTrace();
                    }
                } else if (Objects.equals(taskString[0], Constant.SINGLE_CHARACTER_TASK_TYPE_DEADLINE)) {
                    try {
                        Task task = new Task(taskString[2], TaskTypeEnum.DEADLINE, taskString[3]);
                        if (Objects.equals(taskString[1], "1")) {
                            task.markTaskAsDone();
                        }
                        listOfChatBotContent.add(task);
                    } catch (TaskException e) {
                        e.printStackTrace();
                    }
                } else if (Objects.equals(taskString[0], Constant.SINGLE_CHARACTER_TASK_TYPE_EVENT)) {
                    try {
                        Task task = new Task(taskString[2], TaskTypeEnum.EVENT, taskString[3]);
                        if (Objects.equals(taskString[1], "1")) {
                            task.markTaskAsDone();
                        }
                        listOfChatBotContent.add(task);
                    } catch (TaskException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Unknown command from file.");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(Constant.ERROR_WHILE_LOAD_TASK_LIST_FROM_FILE);
        }
    }

    private Boolean createFileDirectoryIfNotExist(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println(Constant.ERROR_WHILE_CREATE_FILE_DIR);
                return false;
            }
        }
        return true;
    }

    private void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(dukeChatRecordPath.toString());
            for (Task task : listOfChatBotContent) {
                writer.write(task.toFileOutput());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(Constant.ERROR_WHILE_WRITE_TO_FILE);
        }

    }

    public DukeChatBot() {
        listOfChatBotContent = new ArrayList<>();
        loadTaskList();
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
        } catch (TaskException e) {
            System.out.println(Constant.formOutputBySingleString(e.getMessage()));
            return;
        }

        System.out.println(Constant.formOutputByList(Arrays
                .asList("Got it. I've added this task:",
                        "  " + task.toOutput(),
                        "Now you have " + listOfChatBotContent.size() + " tasks in the list.")));
        saveTasksToFile();
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
        saveTasksToFile();
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
