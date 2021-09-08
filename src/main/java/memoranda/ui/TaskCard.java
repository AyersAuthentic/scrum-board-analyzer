package main.java.memoranda.ui;

import main.java.memoranda.model.Task;

public class TaskCard extends Card {
    Task task;

    public TaskCard(Task task){
        super(Integer.toString(task.getTaskNumber()), task.toSimpleMap());
        this.task = task;
    }

    public Task getTask() {return task; }


}
