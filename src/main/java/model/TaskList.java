package model;

import java.util.List;

public interface TaskList {

    List<Task> getTaskList();

    void setTaskList(List<Task> taskList);

    Boolean add(Task task);

    Task remove(int index);

    Task get(int index);
}
