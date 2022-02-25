package model;

import utils.Constant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private String taskName;
    private Boolean isDone;
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
            throw new TaskException(Constant.STRING_ERROR_EMPTY_TASK_NAME);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.taskName = taskName;
            this.isDone = false;
            this.type = type;
            this.specificTime = (type == TaskTypeEnum.TODO) ? null : LocalDate.parse(specificTime, formatter);
        } catch (DateTimeParseException e) {
            throw new TaskException(Constant.STRING_ERROR_INVALID_TIME_FORMAT);
        } catch (RuntimeException e) {
            throw new TaskException(Constant.STRING_ERROR_RUNTIME_FAILURE_PARSING_DATE);
        }
    }

    /**
     * Mark Boolean done as true. The reason why we don't use setter is that there's no markTaskAsUnDone requirement.
     */
    public void markTaskAsDone() {
        this.isDone = true;
    }

    /**
     * Similar with toString(). However, I want to customize it by myself, so I decide not to use toString() name.
     * @return task customize output string.
     */
    public String toOutput() {
        String mark = (isDone) ? "V" : "X";
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
        String mark = (isDone) ? "1" : "0";
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

    public Boolean getIsDone() {
        return isDone;
    }

    public TaskTypeEnum getType() {
        return type;
    }

    public LocalDate getSpecificTime() {
        return specificTime;
    }
}
