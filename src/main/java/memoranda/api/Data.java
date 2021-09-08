package main.java.memoranda.api;

public class Data {

    private TaskData task;
    private ProjectData project;
    private User user;
    private UserStoryData userstory;
    private Values_diff values_diff;


    public Data(){}

    public TaskData getTask() {
        return task;
    }

    public ProjectData getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    public UserStoryData getUserstory() {
        return userstory;
    }

    public Values_diff getValues_diff() {
        return values_diff;
    }

    public void setTask(TaskData task) {
        this.task = task;
    }

    public void setProject(ProjectData project) {
        this.project = project;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserstory(UserStoryData userstory) {
        this.userstory = userstory;
    }

    public void setValues_diff(Values_diff values_diff) {
        this.values_diff = values_diff;
    }
}
