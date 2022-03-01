package temp;

import commons.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Main logic about managing task. This class own a list of task and is also responsible for storing task into local
 * storage.
 */
public class ChatBot {
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
        Path dirPath = path.getParent();
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                System.out.println(Constant.ERROR_WHILE_CREATE_FILE_DIR);
                return false;
            }
        }
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println(Constant.ERROR_WHILE_CREATE_FILE);
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

    private String removeSpaceInFrontOfInput(String input) {
        if (!input.startsWith(" ")) {
            return input;
        }
        return input.replaceFirst("\\s+", "");

    }

    /**
     * The constructor of DukeChatBot. Task list would be initialized here, besides, task information in
     * root/data/duke.txt would be read in this step.
     */
    public ChatBot() {
        listOfChatBotContent = new ArrayList<>();
        loadTaskList();
    }

    /**
     *
     * @param input task name, sometimes it would have a space in front of it, need to be removed.
     * @param type enum, used to identify task type
     * @param specificTime only event/deadline would have time information, it would be parsed by using Task object,
     *                     so we don't need to consider it now.
     */
    public void addStringToList(String input, TaskTypeEnum type, String specificTime) {
        String taskName = removeSpaceInFrontOfInput(input);

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

    /**
     * Delete task form task list with specific index number, should print error message if index number is greater than
     * list size.
     * @param index specific index for deleting task from list.
     */
    public void deleteTaskFromListWithIndex(int index) {
        if (index > listOfChatBotContent.size() || index <= 0) {
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

    /**
     * Mark specific task as done.
     * @param index specific index for marking.
     */
    public void markTaskAsDone(Integer index) {
        if (index > listOfChatBotContent.size() || index <= 0) {
            System.out.println(Constant.ERROR_WHILE_MARK_TASK_AS_DONE);
            return;
        }
        Task task = listOfChatBotContent.get(index - 1);
        task.markTaskAsDone();
        System.out.println(Constant.formOutputByList(Arrays
                .asList(Constant.STRING_MARK_AS_DONE, "  " + task.toOutput())));
    }

    /**
     * Displaying task information which locating in task list.
     */
    public void showListOfChatBotContent() {
        System.out.println(Constant.formOutputByListWithLabel(listOfChatBotContent, Constant.STRING_SHOW_LIST));
    }

    /**
     * Using keyword to filter list of task by task name. Use stream() in this method.
     * @param keyword keyword to filter task list
     */
    public void showListOfChatBotContentWithKeyWord(String keyword) {
        String taskName = removeSpaceInFrontOfInput(keyword);
        if (Objects.equals(taskName, "")) {
            System.out.println(Constant.ERROR_WHILE_FIND_TASK_WITH_EMPTY_KEYWORD);
            return;
        }
        System.out.println(Constant.formOutputByListWithLabel(
                listOfChatBotContent.stream()
                        .filter(task -> task.getTaskName().contains(taskName)).collect(Collectors.toList()),
                Constant.STRING_SHOW_MATCHED_LIST
        ));
    }

    /**
     * Getter for task list, only used in test.
     * @return task list
     */
    public List<Task> getListOfChatBotContent() {
        return listOfChatBotContent;
    }
}
