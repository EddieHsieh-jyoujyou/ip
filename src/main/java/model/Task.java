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

    /**
     * Default constructor
     */
    public Task() {

    }

    /**
     * Constructor for Task by passing taskName, type and time. The reason why I customize TaskException is that I
     * don't want Task be created successfully if there's an illegal input. Once the parameters is invalid, I hope
     * this constructor would throw a TaskException to inform above method that there's an error and print out the reason
     * by using Constant.formOutputBySingleString
     * @param taskName name of the task
     * @param type type of the task
     * @param specificTime time for task stretch information, only valid while type is event/deadline
     * @throws TaskException Once the taskName/time format is invalid, exception would be thrown
     */
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
            throw new TaskException("Time format invalid. Format should be yyyy-MM-dd");
        } catch (RuntimeException e) {
            throw new TaskException("Runtime exception while parsing date.");
        }
    }

    /**
     * Mark Boolean done as true. The reason why we don't use setter is that there's no markTaskAsUnDone requirement.
     */
    public void markTaskAsDone() {
        this.done = true;
    }

    /**
     * Similar with toString(). However, I want to customize it by myself, so I decide not to use toString() name.
     * @return task customize output string.
     */
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

    /**
     * Similar with toOutput. However, the string would be saved in file, which is used while application re-active.
     * It should be same as the command format which user should type.
     * @return task customize output string.
     */
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

    /**
     * Getter for task name.
     * @return task name
     */
    public String getTaskName() {
        return taskName;
    }
}