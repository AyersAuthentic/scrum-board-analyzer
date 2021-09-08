package main.java.memoranda.api;

import main.java.memoranda.Task;

public class UserStoryData {

    private String id;
    private String milestone;
    private String milestone_name;
    private String ref;
    private String subject;
    private String created_date;
    private String total_points;
    private TaskData[] taskData;


    public UserStoryData(){};

    public String getId() {
        return id;
    }

    public String getMilestone() {
        return milestone;
    }

    public String getMilestone_name() {
        return milestone_name;
    }

    public String getRef() {
        return ref;
    }

    public String getSubject() {
        return subject;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getTotal_points() {
        return total_points;
    }

    public TaskData[] getTaskData() {
        return taskData;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public void setMilestone_name(String milestone_name) {
        this.milestone_name = milestone_name;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTotal_points(String total_points) {
        this.total_points = total_points;
    }

    public void setTaskData(TaskData[] taskData) {
        this.taskData = taskData;
    }

    @Override
    public String toString(){
        return ("UserStory Ref Number: " + ref + " Name: " + milestone_name + " Subject: " + subject);
    }
}
