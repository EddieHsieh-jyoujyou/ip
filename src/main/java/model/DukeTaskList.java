package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DukeTaskList implements TaskList {
    private List<Task> taskList;

    public DukeTaskList() {
        this.taskList = Collections.synchronizedList(new ArrayList<Task>());
    }

    @Override
    public List<Task> getTaskList() {
        return taskList;
    }

    @Override
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public Boolean add(Task task) {
        return taskList.add(task);
    }

    @Override
    public Task remove(int index) {
        if (index < 0 || index + 1 > taskList.size()) {
            return null;
        }
        return taskList.remove(index);
    }

    @Override
    public Task get(int index) {
        if (index < 0 || index + 1 > taskList.size()) {
            return null;
        }
        return taskList.get(index);
    }
}
