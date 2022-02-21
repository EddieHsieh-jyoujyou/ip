import java.util.Objects;

public class Task {
    private String taskName;
    private Boolean done;
    private String type;
    private String specificTime;

    public Task() {

    }

    public Task(String taskName, String type, String specificTime) throws RuntimeException {
        if (taskName.length() == 0) {
            throw new RuntimeException("Task name should not be empty.");
        }
        if ((Objects.equals(type, "D") || Objects.equals(type, "E")) && specificTime.length() == 0) {
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
        if (Objects.equals(type, "D")) {
            time = "(by: " + specificTime + ")";
        } else if (Objects.equals(type, "E")) {
            time = "(at: " + specificTime + ")";
        } else {
            time = specificTime;
        }
        return "[" + type + "][" + mark + "] " + taskName + " " + time;
    }
}
