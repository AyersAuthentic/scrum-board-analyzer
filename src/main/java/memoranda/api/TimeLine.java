package main.java.memoranda.api;

public class TimeLine {

    private Data data;
    private String id;
    private String content_type;
    private String object_id;
    private String event_type;
    private String project;
    private String created;


    public TimeLine(){};

    public Data getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public String getContent_type() {
        return content_type;
    }

    public String getObject_id() {
        return object_id;
    }

    public String getEvent_type() {
        return event_type;
    }

    public String getProject() {
        return project;
    }

    public String getCreated() {
        return created;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
