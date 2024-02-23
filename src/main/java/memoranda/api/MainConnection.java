//Task #106 Branch

package main.java.memoranda.api;

import main.java.memoranda.model.*;
import main.java.memoranda.model.Event;
import main.java.memoranda.model.Project;

import java.io.IOException;

public class MainConnection {

    private static String username;
    private static String password;
    private static User user;
    private static ProjectData[] projects;

    public static void connect(String username, String password) throws IOException {

        username = username;
        password = password;
        user = new User();
        user = UserAPI.loginUser(user, username, password);
        projects = new ProjectData[100];
        projects = loadData(user ,projects);
        DataStore.initialize(projects);

    }



    public static void main(String[] args) throws IOException {
        MainConnection.connect("kaayers1", "myPassword");
    }


    public static ProjectData[] loadData(User user, ProjectData[] projects) throws IOException {

        projects = ProjectAPI.populateProjects(user, projects);

        return projects;

    }


}
