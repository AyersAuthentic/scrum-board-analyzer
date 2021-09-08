package main.java.memoranda.ui.data;

import main.java.memoranda.model.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

/**
* Generates a simple example Taiga King 'Project' for use in UI development
*  
* @author Lebkuchen Team
* @version 0.9
*/
public class PlaceholderDataStore {
    public static Project project;
    public static UserStory story;
  
    public static void initialize(){
        LinkedList<Collaborator> collabList = new LinkedList<Collaborator>();
        LinkedList<Sprint> sprintList = new LinkedList<Sprint>();
        for(int i = 0; i < 5; i++){
          collabList.add(new Collaborator("collab" + i, "collaborator", "" + i, "icon" + i)); 
        } 
        List<Event> timeline = new LinkedList<Event>();
        timeline.add(new Event(1, collabList.get(0), "2020-04-01", "event1", State.READY, State.IN_PROGRESS));
        timeline.add(new Event(1, collabList.get(0), "2020-04-02", "event2", State.IN_PROGRESS, State.TESTING));
        timeline.add(new Event(1, collabList.get(0), "2020-04-03", "event3", State.TESTING, State.IN_PROGRESS));
        timeline.add(new Event(1, collabList.get(0), "2020-04-04", "event4", State.IN_PROGRESS, State.TESTING));
        timeline.add(new Event(1, collabList.get(0), "2020-04-05", "event5", State.TESTING, State.COMPLETE));
  
        timeline.add(new Event(2, collabList.get(1), "2020-04-01", "event6", State.READY, State.IN_PROGRESS));
        timeline.add(new Event(2, collabList.get(1), "2020-04-02", "event7", State.IN_PROGRESS, State.READY));
        timeline.add(new Event(2, collabList.get(1), "2020-04-03", "event8", State.READY, State.COMPLETE));
        
        timeline.add(new Event(3, collabList.get(2), "2020-04-10", "event9", State.READY, State.IN_PROGRESS));
        timeline.add(new Event(3, collabList.get(2), "2020-04-11", "event10", State.IN_PROGRESS, State.TESTING));
        
        Task task1 = new Task(1, "This is a task", "This is a description", "2020-04-01", timeline.subList(0, 5), State.COMPLETE, collabList.get(0));
        Task task2 = new Task(2, "This is another task", "This is another description", "2020-04-01", timeline.subList(5, 8), State.COMPLETE, collabList.get(1));
        Task task3 = new Task(3, "One more task", "One more description", "2020-04-10", timeline.subList(8, 10), State.TESTING, collabList.get(2));
        Task task4 = new Task(4, "Unclaimed task", "Unclaimed", "2020-04-03", new LinkedList<Event>(), State.READY, null);
        
        HashMap<Integer, Task> tasks1 = new HashMap<>();
        tasks1.put(1, task1);
        tasks1.put(2, task2);
       
        HashMap<Integer, Task> tasks2 = new HashMap<>();
        tasks2.put(4, task4);

        HashMap<Integer, Task> tasks3 = new HashMap<>();
        tasks3.put(3, task3);   
        
        HashMap<String, Collaborator> us1Collab = new HashMap<>();
        us1Collab.put("collab0", collabList.get(0));
        us1Collab.put("collab1", collabList.get(1));
        UserStory story1 = new UserStory("This is a US", "US description", "2020-04-01", "2020-04-01", 1, tasks1, us1Collab, timeline.subList(0, 8), 3);
        story1.setTasks(tasks1);

        HashMap<String, Collaborator> us2Collab = new HashMap<>();
        UserStory story2 = new UserStory("This is another US", "Another US description", "2020-04-01", "2020-04-01", 2, tasks2, us2Collab, new LinkedList<Event>(), 3);
        story2.setTasks(tasks2);

        HashMap<String, Collaborator> us3Collab = new HashMap<>();
        us3Collab.put("collab2", collabList.get(2));
        UserStory story3 = new UserStory("A US", "A US description", "2020-04-10", "2020-04-10", 3, tasks3, us3Collab, timeline.subList(8, 10), 3);

        HashMap<Integer, UserStory> stories1 = new HashMap<>();
        stories1.put(1, story1);
        stories1.put(2, story2);
        story = story1;
 
        HashMap<Integer, UserStory> stories2 = new HashMap<>();
        stories2.put(3, story3);
 
        Sprint sprint1 = new Sprint("This is a sprint", "2020-04-01", "2020-04-08", timeline.subList(0, 8), stories1, us1Collab, "burndown");
        sprint1.setStories(stories1);
        Sprint sprint2 = new Sprint("This is another sprint", "2020-04-10", "2020-04-17", timeline.subList(8, 10), stories2, us3Collab, "another burndown");

        HashMap<String, Sprint> sprints = new HashMap<>();
        sprints.put("This is a sprint", sprint1);
        sprints.put("This is another sprint", sprint2);
      
        HashMap<String, Collaborator> collaborators = new HashMap<>();
        for(Collaborator collab : collabList){
            collaborators.put(collab.getUsername(), collab);
        }
        project = new Project("This is a project", "An amazing project", 1, timeline, collaborators, sprints); 
    }


    public static void main(String[] args){
      PlaceholderDataStore.initialize();
      Project project = PlaceholderDataStore.project;
      System.out.println("Project title: " + project.getName());
      for(String sprintName : project.getSprintNames()){
        Sprint sprint = project.getSprint(sprintName);
        System.out.println("  Sprint name: " + sprint.getName());
        for(int usNumber : sprint.getUserStoryNumbers()){
          UserStory story = sprint.getUserStory(usNumber);
          System.out.println("    Userstory" + story.getUserStoryNumber() + " title: " + story.getTitle()); 
          for(int taskNumber : story.getTaskNumbers()){
            Task task = story.getTask(taskNumber);
            System.out.println("      Task" + task.getTaskNumber() + " title: " + task.getTitle()); 
          } 
        }
      }
    }
}
