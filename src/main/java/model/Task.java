package model;

import utils.Constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private String taskName;
    private Boolean done;
    private TaskTypeEnum type;
    private LocalDate specificTime;

    public Task() {

    }

    public Task(String taskName, TaskTypeEnum type, String specificTime) throws TaskException {
        if (taskName.length() == 0) {
            throw new TaskException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        if ((type == TaskTypeEnum.DEADLINE || type == TaskTypeEnum.EVENT) && specificTime.length() == 0) {
            throw new TaskException("Specific time should not be empty.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate time;
        try {
            time = (type == TaskTypeEnum.TODO) ? null : LocalDate.parse(specificTime, formatter);
            this.taskName = taskName;
            this.done = false;
            this.type = type;
            this.specificTime = time;
        } catch (DateTimeParseException e) {
            throw new TaskException("Time format invalid");
        } catch (RuntimeException e) {
            throw new TaskException("Runtime exception while parsing date.");
        }
    }

    public void markTaskAsDone() {
        this.done = true;
    }

    public String toOutput() {
        String mark = (done) ? "✓" : "✗";
        String time;
        String typeCharacter;
        switch (type) {
            case DEADLINE:
                time = " (by: " + specificTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_DEADLINE;
                break;
            case EVENT:
                time = " (at: " + specificTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_EVENT;
                break;
            default:
                time = "";
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_TODO;
        }
        return "[" + typeCharacter + "][" + mark + "] " + taskName + time;
    }

    public String toFileOutput() {
        String mark = (done) ? "1" : "0";
        String time;
        String typeCharacter;
        switch (type) {
            case DEADLINE:
                time = " | " + specificTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_DEADLINE;
                break;
            case EVENT:
                time = " | " + specificTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_EVENT;
                break;
            default:
                time = "";
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_TODO;
        }
        return typeCharacter + " | " + mark + " | " + taskName + time + "\n";
    }
}
