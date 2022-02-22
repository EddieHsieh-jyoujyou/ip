package model;

/**
 * Customize exception for detecting whether there's exception while construct Task.
 */
public class TaskException extends Exception {
    public TaskException(String reason) {
        super(reason);
    }
}
