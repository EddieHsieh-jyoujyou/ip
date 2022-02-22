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

    public String toOutput() {
        String mark = (done) ? "✓" : "✗";
        String time;
        String typeCharacter;
        switch (type) {
            case DEADLINE:
                time = " (by: " + specificTime + ")";
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_DEADLINE;
                break;
            case EVENT:
                time = " (at: " + specificTime + ")";
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_EVENT;
                break;
            default:
                time = specificTime;
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
                time = " | " + specificTime;
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_DEADLINE;
                break;
            case EVENT:
                time = " | " + specificTime;
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_EVENT;
                break;
            default:
                time = specificTime;
                typeCharacter = Constant.SINGLE_CHARACTER_TASK_TYPE_TODO;
        }
        return typeCharacter + " | " + mark + " | " + taskName + time + "\n";
    }
}
