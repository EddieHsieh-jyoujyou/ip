package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import commons.Constant;
import model.DukeTaskList;
import model.Task;
import model.TaskList;
import model.TaskTypeEnum;
import model.exceptions.TaskException;

public class DukeStorage implements Storage {
    private final Path path;

    /**
     * Constructor of DukeStorage.
     * Will create file for storage while object is constructed.
     *
     * @throws IOException create file failed.
     */
    public DukeStorage() throws IOException {
        this.path = Paths.get(
                FileSystems.getDefault().getPath("").toAbsolutePath().toString(),
                "data", "duke.txt");
        createFileDirectoryIfNotExist(path);
    }

    @Override
    public void save(TaskList list) {
        saveTasksToFile(path, list);
    }

    @Override
    public TaskList read() {
        return loadTaskListFromFile(path);
    }

    private void createFileDirectoryIfNotExist(Path path) throws IOException {
        Path dirPath = path.getParent();
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    private TaskList loadTaskListFromFile(Path path) {
        File dukeChatRecord = new File(path.toString());
        TaskList taskList = new DukeTaskList();
        try {
            Scanner scanner = new Scanner(dukeChatRecord);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String [] taskString = input.split(" \\| ");
                if (Objects.equals(taskString[0], Constant.SINGLE_CHARACTER_TASK_TYPE_TODO)) {
                    try {
                        Task task = new Task(taskString[2], TaskTypeEnum.TODO, null);
                        if (Objects.equals(taskString[1], "1")) {
                            task.markTaskAsDone();
                        }
                        taskList.add(task);
                    } catch (TaskException e) {
                        e.printStackTrace();
                    }
                } else if (Objects.equals(taskString[0], Constant.SINGLE_CHARACTER_TASK_TYPE_DEADLINE)) {
                    try {
                        Task task = new Task(taskString[2], TaskTypeEnum.DEADLINE, LocalDate.parse(taskString[3]));
                        if (Objects.equals(taskString[1], "1")) {
                            task.markTaskAsDone();
                        }
                        taskList.add(task);
                    } catch (TaskException e) {
                        e.printStackTrace();
                    }
                } else if (Objects.equals(taskString[0], Constant.SINGLE_CHARACTER_TASK_TYPE_EVENT)) {
                    try {
                        Task task = new Task(taskString[2], TaskTypeEnum.EVENT, LocalDate.parse(taskString[3]));
                        if (Objects.equals(taskString[1], "1")) {
                            task.markTaskAsDone();
                        }
                        taskList.add(task);
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
        return taskList;
    }

    private void saveTasksToFile(Path path, TaskList list) {
        try {
            FileWriter writer = new FileWriter(path.toString());
            for (Task task : list.getTaskList()) {
                writer.write(task.toFileOutput());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(Constant.ERROR_WHILE_WRITE_TO_FILE);
        }

    }
}
