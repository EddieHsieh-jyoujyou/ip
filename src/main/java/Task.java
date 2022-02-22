public class Task {
    private String taskName;
    private Boolean done;
    private TaskTypeEnum type;
    private String specificTime;

    public Task() {

    }

    public Task(String taskName, TaskTypeEnum type, String specificTime) throws RuntimeException {
        if (taskName.length() == 0) {
            throw new RuntimeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        if ((type == TaskTypeEnum.DEADLINE || type == TaskTypeEnum.EVENT) && specificTime.length() == 0) {
            throw new RuntimeException("Specific time should not be empty.");
        }
        this.taskName = taskName;
        this.done = false;
        this.type = type;
        this.specificTime = specificTime;
    }

    public void markTaskAsDone() {
        this.done = true;
    }

    public String getTaskName() {
        return taskName;
    }

    public Boolean getDone() {
        return done;
    }

    public String toOutput() {
        String mark = (done) ? "✓" : "✗";
        String time;
        String typeCharacter;
        switch (type) {
            case DEADLINE:
                time = " (by: " + specificTime + ")";
                typeCharacter = "D";
                break;
            case EVENT:
                time = " (at: " + specificTime + ")";
                typeCharacter = "E";
                break;
            default:
                time = specificTime;
                typeCharacter = "T";
        }
        return "[" + typeCharacter + "][" + mark + "] " + taskName + time;
    }
}
