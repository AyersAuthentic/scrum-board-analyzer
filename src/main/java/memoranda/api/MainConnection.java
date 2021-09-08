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
        Project project = DataStore.projects.get("Lebkuchen");
        System.out.println(project.getName());
        System.out.println(project.getDescription());
        project.getSprints().forEach((key,sprint)->{
            System.out.println(sprint.getName());
            System.out.println(sprint.getStartDate());
            System.out.println(sprint.getEndDate());
            System.out.println("Sprint colloborators--> ");
            for(String name: sprint.getCollaboratorFull_Name()){
                System.out.println(name);
            }
            sprint.getStories().forEach((key2, story)-> {
                System.out.print("User Story--> ");
                System.out.print(story.getUserStoryNumber() + " ");
                System.out.println(story.getTitle());
                System.out.println("These are the user story collabs--> ");
                for(String name: story.getCollaboratorNames()){
                    System.out.println(name);
                }
                System.out.println("Tasks--> ");
                story.getTasks().forEach((key3, task)-> {
                    System.out.print(task.getTaskNumber() + " ");
                    System.out.print(task.getTitle() + " ");
                    System.out.println(task.getClaimant().getFullName());
                });
                System.out.println(" ");
                System.out.println(" ");
            });
        });

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Testing out events-->");
        for(Event event : project.getTimeline()){
            System.out.println(event.getInitiator().getFullName() + " Event Type: " + event.getDescription() + "States: " + event.getStartState() + " " + event.getEndState());
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Testing collaboratos--> ");
        project.getCollaborators().forEach((key, value)->{
            System.out.print("Key: " + key + " ");
            System.out.println("First Name: " + value.getFirstName());

        });



    }


    public static ProjectData[] loadData(User user, ProjectData[] projects) throws IOException {

        projects = ProjectAPI.populateProjects(user, projects);

        return projects;

    }


}
