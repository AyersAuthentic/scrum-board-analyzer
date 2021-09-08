package main.java.memoranda.api;

public class SprintData {

    private String project;
    private String id;
    private String name;
    private String estimated_start;
    private String estimated_finish;
    private String created_date;
    private boolean closed;
    private UserStoryData[] user_stories;

    public SprintData(){};

    public String getProject() {
        return project;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEstimated_start() {
        return estimated_start;
    }

    public String getEstimated_finish() {
        return estimated_finish;
    }

    public String getCreated_date() {
        return created_date;
    }

    public boolean isClosed() {
        return closed;
    }

    public UserStoryData[] getUser_stories() {
        return user_stories;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEstimated_start(String estimated_start) {
        this.estimated_start = estimated_start;
    }

    public void setEstimated_finish(String estimated_finish) {
        this.estimated_finish = estimated_finish;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setUser_stories(UserStoryData[] user_stories) {
        this.user_stories = user_stories;
    }

    @Override
    public String toString(){
        return "Sprint [ name: "+name+" , Estimaged Start: "+estimated_start+" , Estimated Finish: "+estimated_finish+
                "Sprint userStories[0] ID:" + user_stories[0].getId()+ " ]";
    }
}
