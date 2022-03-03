package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import commons.Constant;
import commons.util.io.OutputInterface;
import logic.command.Command;
import logic.parser.Parser;
import logic.parser.StorageParser;
import logic.parser.exceptions.ParseException;
import model.DukeTaskList;
import model.Task;
import model.TaskList;
import model.exceptions.TaskException;

public class DukeStorage implements Storage {
    private final Path path;
    private final Parser parser;

    /**
     * Constructor of DukeStorage.
     * Will create file for storage while object is constructed.
     *
     * @throws IOException create file failed.
     */
    public DukeStorage() throws IOException {
        this.parser = new StorageParser();
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
        File dukeChatRecordFile = new File(path.toString());
        TaskList taskList = new DukeTaskList();
        try {
            Scanner scanner = new Scanner(dukeChatRecordFile);
            while (scanner.hasNextLine()) {
                parseInput(scanner.nextLine(), taskList);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(Constant.ERROR_WHILE_LOAD_TASK_LIST_FROM_FILE);
        }
        return taskList;
    }

    private void parseInput(String input, TaskList list) {
        try {
            Command command = parser.parseCommand(input);
            command.execute(list);
        } catch (ParseException | TaskException e) {
            OutputInterface.writer(e.getMessage());
        }
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
