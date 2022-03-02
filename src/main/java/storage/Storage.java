package storage;

import model.TaskList;

public interface Storage {

    void save(TaskList list);

    TaskList read();
}
