package main.java.memoranda.model;

import main.java.memoranda.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


// Add method to get all project names

public class DataStore {

    public static HashMap<String, Project> projects;

    public static void initialize(ProjectData[] projectData){

        projects = new HashMap<String, Project>();

        for(ProjectData data: projectData){
            String name = data.getName().replaceAll("(\\r|\\n)", "");
            projects.put(name, new Project(data));
        }
    }

    public static Project getProject(String projectName){
        return projects.get(projectName);
    }
    
    public static List<String> getProjectNames(){
      return new ArrayList<String>(projects.keySet());
    }
}
