package main.java.memoranda.api;

public class User {
    private String id;
    private String name;
    private String username;
    private String full_name_display;
    private String photo;
    private String gravatar_id;
    private String data_joined;
    private String auth_token;

    public User(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getFull_name_display() {
        return full_name_display;
    }

    public String getPhoto() {
        return photo;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }


    public String getData_joined() {
        return data_joined;
    }

    public String getAuth_token() {
        return String.format("${%s}", auth_token);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setFull_name_display(String full_name_display) {
        this.full_name_display = full_name_display;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }


    public void setData_joined(String data_joined){
        this.data_joined = data_joined;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = String.format("${%s}", auth_token);
    }

    @Override
    public String toString(){
        return "user [ id: "+id+" , username: "+username+" , full_name_display: "+full_name_display+
                " , gravatar_id: "+gravatar_id+ " ]";
    }
}
