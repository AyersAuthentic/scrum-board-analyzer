package main.java.memoranda.api;

public class TaskData {

    private String id;
    private String project;
    private String ref;
    private String owner;
    private String user_story;
    private String created_date;
    private String modified_date;
    private String finished_date;
    private String subject;
    private Status_extra_info status_extra_info;
    private Owner_extra_info owner_extra_info;
    private Assigned_to_extra_info assigned_to_extra_info;
    private User_story_extra_info user_story_extra_info;


    public TaskData(){}

    public String getId() {
        return id;
    }

    public String getProject() {
        return project;
    }

    public String getRef() {
        return ref;
    }

    public String getOwner() {
        return owner;
    }

    public String getUser_story() {
        return user_story;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getModified_date() {
        return modified_date;
    }

    public String getFinished_date() {
        return finished_date;
    }

    public String getSubject(){
        return subject;
    }

    public Status_extra_info getStatus_extra_info() {
        return status_extra_info;
    }

    public Assigned_to_extra_info getAssigned_to_extra_info() {
        return assigned_to_extra_info;
    }

    public Owner_extra_info getOwner_extra_info() {
        return owner_extra_info;
    }

    public User_story_extra_info getUser_story_extra_info() {
        return user_story_extra_info;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setUser_story(String user_story) {
        this.user_story = user_story;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public void setFinished_date(String finished_date) {
        this.finished_date = finished_date;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public void setStatus_extra_info(Status_extra_info status_extra_info) {
        this.status_extra_info = status_extra_info;
    }

    public void setAssigned_to_extra_info(Assigned_to_extra_info assigned_to_extra_info) {
        this.assigned_to_extra_info = assigned_to_extra_info;
    }

    public void setOwner_extra_info(Owner_extra_info owner_extra_info) {
        this.owner_extra_info = owner_extra_info;
    }

    public void setUser_story_extra_info(User_story_extra_info user_story_extra_info) {
        this.user_story_extra_info = user_story_extra_info;
    }

    @Override
    public String toString(){
        return ("Task Reference Number: " + ref + " Owner ID: " + owner + " Created Date: " + created_date + ",\n" +
        "Finished Date: " + finished_date + " Assigned to: " + owner_extra_info.getFull_name_display() + " Subject: " + subject);
    }
}
