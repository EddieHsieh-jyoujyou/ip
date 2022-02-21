public class Task {
    private String taskName;
    private Boolean done;

    public Task() {

    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
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
        return "[" + mark + "] " + taskName;
    }
}
