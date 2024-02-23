package main.java.memoranda.model;

import main.java.memoranda.api.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.LinkedList;

/**
* Defines the User Story class, Taiga King's internal representation of a Taiga user story

*  
* @author Lebkuchen Team
* @version 0.9
*/
public class UserStory {
  private String title;
  private String description;
  private LocalDate dateCreatedDate;
  private LocalDate dateAddedToProductBacklog;
  private LocalDate dateAddedToSprintBacklog;
  private int userStoryNumber;
  private HashMap<Integer, Task> tasks;
  private HashMap<String, Collaborator> collaborators;
  private List<Event> timeline;
  private int numPoints;
  private Project project;

  //With have to set up load for collaborators and tasks

  public UserStory(UserStoryData userStoryData,
                   HashMap<String, Collaborator> collaborators, Project project){

    this.title = userStoryData.getSubject();
    this.description = null;
    this.dateCreatedDate = LocalDate.parse(userStoryData.getCreated_date().substring(0,10));
    this.dateAddedToProductBacklog = dateCreatedDate;
    this.userStoryNumber = Integer.parseInt(userStoryData.getRef());
    try{
      this.numPoints = (int)Double.parseDouble(userStoryData.getTotal_points());
    }catch(NullPointerException e){
      this.numPoints = 0;
    }
    this.project = project;
    this.tasks = new HashMap<>();

    for(TaskData task: userStoryData.getTaskData()){
      tasks.put(Integer.parseInt(task.getRef()), new Task(task, project));
    }
    timeline = new LinkedList<>();
    for(Task task : tasks.values()){
      List<Event> taskTimeline = task.getTimeline();
      for(Event event : taskTimeline){
        timeline.add(event);
      }
    }

    this.collaborators = new HashMap<>();
      for(Task task : tasks.values()){
        Collaborator claimant = task.getClaimant();
        if (claimant != null && !claimant.getFirstName().equals("Unclaimed")){
          this.collaborators.put(claimant.getFullName(), claimant);
      }
    }
  }


  public UserStory(String title, String description, String addedProduct, String addedSprint, int number, 
		HashMap<Integer, Task> tasks, HashMap<String, Collaborator> collaborators, List<Event> timeline, int points){
    this.title = title;
    this.description = description;
    dateAddedToProductBacklog = LocalDate.parse(addedProduct);
    dateAddedToSprintBacklog = LocalDate.parse(addedSprint);
    userStoryNumber = number;
    this.tasks = tasks;
    this.timeline = timeline;
    numPoints = points;
    this.collaborators = collaborators;
  }

  /**
  * Gets the number of the user story
  * @return the number of the user story
  */
  public int getUserStoryNumber(){
    return userStoryNumber;
  }

  /**
  * Gets the title of the user story
  * @return the title of the user story
  */
  public String getTitle(){
    return title;
  }

  /**
  * Gets the description of the user story
  * @return the description of the user story 
  */
  public String getDescription(){
    return description;
  }

  /**
  * Gets the date the user story was added to the product backlog
  * @return the date the user story was added to the product backlog 
  */ 
  public LocalDate getDateAddedProduct(){
    return dateAddedToProductBacklog;
  }

  /**
  * Gets the date the user story was added to the sprint backlog 
  * @return the date the user story was added to the sprint backlog 
  */
  public LocalDate getDateAddedSprint(){
    return dateAddedToSprintBacklog; 
  }

  public HashMap<Integer, Task> getTasks() {
    return tasks;
  }

  public void setTasks(HashMap<Integer, Task> tasks) {
    this.tasks = tasks;
  }

  /**
  * Gets the date the user story was completed, if it has been
  * @return the date the user story was completed or null
  */
  public LocalDate getCompletionDate(){
    if(tasks.size() == 0){
      return null;
    }
    LocalDate lastTaskCompletion = LocalDate.parse("0000-01-01");
    for(Task task : tasks.values()){
      if(task.completed() != true){
        return null;
      } else if (task.getCompletionDate().compareTo(lastTaskCompletion) <= 0){
        continue;
      } else {
        lastTaskCompletion = task.getCompletionDate();
      }
    }
    return lastTaskCompletion;  
  }

  /**
  * Determines whether the user story has been completed
  * @return a boolean indicating whether the user story has been completed 
  */
  public boolean isFinished(){
    for(Task task : tasks.values()){
      if(task.completed() != true){
        return false;
      }
    }
    return true;
  }

  /**
  * Get the numbers of all tasks associated with the user story
  * @return a list of the numbers of all tasks associated with the user story
  */
  public List<Integer> getTaskNumbers(){
    List<Integer> numbers = new LinkedList<Integer>(tasks.keySet()); 
    return numbers;	    
  }

  /**
  * Gets a particular task based on the task's number
  * @param taskNumber is the number associated with a task on Taiga
  * @return the task associated with a task number
  */
  public Task getTask(int taskNumber){
    return tasks.get(taskNumber);
  }

  /**
  * Gets all of the events associated with the user story
  * @return a list of all of the events associated with the user story 
  */
  public List<Event> getTimeline(){
    return timeline;
  }

  /**
  * Gets all of the events associated with the user story on a given day
  * @param date is the date of interest for the event list data form "yyyy-MM-dd"
  * @return a list of all of the events associated with the user story on a given day
  */
  public List<Event> getDaysTimeline(String date){
    List<Event> dayTimeline = new LinkedList<>();
    for(Event event : timeline){
      if(event.getDate().equals(LocalDate.parse(date))){
        dayTimeline.add(event);
      }
    }
    return dayTimeline;
  }

  /**
  * Gets the number of tasks associated with the story that are in the ready state
  * @return the number of tasks associated with the story that are in the ready state
  */
  public int getNumTasksInReady(){
    int count = 0;
    for(Task task : tasks.values()){
      if(task.getState().compareTo(State.READY) == 0){
        count++;
      }
    }
    return count;
  }

  /**
  * Gets the number of tasks associated with the story that are in the complete state
  * @return the number of tasks associated with the story that are in the complete state
  */
  public int getNumTasksInComplete(){
    int count = 0;
    for(Task task : tasks.values()){
      if(task.getState().compareTo(State.COMPLETE) == 0){
        count++;
      }
    }
    return count;
  }




  /**
  * Gets the number of tasks associated with the story that are in the testing state
  * @return the number of tasks associated with the story that are in the testing state
  */
  public int getNumTasksInTest(){
    int count = 0;
    for(Task task : tasks.values()){
      if(task.getState().compareTo(State.TESTING) == 0){
        count++;
      }
    }
    return count;
  }

  /**
  * Gets the total number of tasks associated with the user story
  * @return the total number of tasks associated with the user story 
  */
  public int getNumTotalTasks(){
    return getNumTasksInTest() + getNumTasksInReady() + getNumTasksInComplete();
  }

  /**
  * Gets the total number of incorrect moves associated with the user story
  * @return the total number of incorrect moves associated with the user story
  */
  public int getNumIncorrectMoves(){
    int count = 0;
    for(Task task : tasks.values()){
      count += task.getNumIncorrectMoves();
    }
    return count;
  }

  /**
  * Gets the total number of incorrect moves associated with a collaborator
  * @return the total number of incorrect moves associated with a collaborator
  */
  public int getNumIncorrectMovesCollaborator(Collaborator collaborator){
    int count = 0;
    for(Task task : tasks.values()){
      count += task.getNumIncorrectMovesCollaborator(collaborator);
    }
    return count;
  }

  /**
  * Gets the number of points the user story is worth
  * @return the number of points the user story is worth
  */
  public int getNumPoints(){
    return numPoints;
  }
  
  /**
  * Get collaborator full names of those who have partcipated in the user story
  * @return a list of collaborator full names
  */
  public List<String> getCollaboratorNames(){
    return new LinkedList<String>(collaborators.keySet());
  }

  /**
  * Get collaborator based on username
  * @param full_name is the full name of the collaborator of interest
  * @return collaborator of interest
  */
  public Collaborator getCollaborator(String full_name){
    return collaborators.get(full_name);
  }

  /**
  * Gets a list of task numbers associated with a particular collaborator
  * @param collaborator is the collaborator of interest
  * @return a list of task numbers associated with a particular collaborator
  */
  public List<Integer> getCollaboratorTaskNumbers(Collaborator collaborator){
    List<Integer> numbers = new LinkedList<>();
    for(Task task : tasks.values()){
      Collaborator claimant = task.getClaimant();
      if (claimant != null && !claimant.getFirstName().equals("Unclaimed")){
        this.collaborators.put(claimant.getFullName(), claimant);
      }
    }
    return numbers;
  }





  public LinkedHashMap<String, String> toSimpleMap(){
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put("Title", getTitle());
    map.put("User Story Number", Integer.toString(getUserStoryNumber()));
    map.put("# Tasks", Integer.toString(getNumTotalTasks()));
    return map;
  }


}
