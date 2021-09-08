package main.java.memoranda.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class MembershipData {

    private String user;
    private String project;
    private String created_at;
    private String full_name;
    private String is_user_active;
    private String is_owner;
    private String photo;
    private String project_name;
    private String role_name;


    public MembershipData(){}

    public String getUser() {
        return user;
    }

    public String getProject() {
        return project;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getIs_user_active() {
        return is_user_active;
    }

    public String getIs_owner() {
        return is_owner;
    }

    public String getPhoto() {
        return photo;
    }

    public String getProject_name() {
        return project_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setIs_user_active(String is_user_active) {
        this.is_user_active = is_user_active;
    }

    public void setIs_owner(String is_owner) {
        this.is_owner = is_owner;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }


    public static MembershipData[] populateMemberships(User user, String projectID, MembershipData[] members) throws IOException {

        RequestConfig config = new RequestConfig();
        config.setParams("memberships?project=" + projectID);

        String respString = Request.get(API_CONFIG.API_URL, user, config);


        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        members = gson.fromJson(respString, MembershipData[].class);

        return members;


    }
}
