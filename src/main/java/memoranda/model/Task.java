package main.java.memoranda.model;

import main.java.memoranda.api.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;
import java.time.LocalDate;

/**
* Defines the Task class, Taiga King's internal representation of a Taiga Task
*  
* @author Lebkuchen Team
* @version 0.9
*/

public class Task {
  private String title;
  private String description;
  private LocalDate dateAdded;
  private List<Event> timeline;
  private int taskNumber;
  private State state;
  private Collaborator claimant;

  public Task(TaskData taskData, Project project){

    this.title = taskData.getSubject();
    this.description = null;
    this.dateAdded = LocalDate.parse(taskData.getCreated_date().substring(0,10));
    this.taskNumber = Integer.parseInt(taskData.getRef());
    this.state = State.stringToState(taskData.getStatus_extra_info().getName());
//    System.out.println("\nThis is the task taskData claiment");
//    try{
//      System.out.println(taskData.getAssigned_to_extra_info().getFull_name_display());
//    }catch (NullPointerException e){
//      System.out.println("No one has claimed this task");
//    }
//    System.out.println("This is the project collaborator name");
//    try{
//      System.out.println(project.getCollaborator(taskData.getAssigned_to_extra_info().getFull_name_display()).getFullName());
//    }catch (NullPointerException e){
//      System.out.println("There is no project collaborator name because no one has claimed the task");
//    }
//    System.out.println("---------");

    try{
      this.claimant = project.getCollaborator(taskData.getAssigned_to_extra_info().getFull_name_display());
    }catch (NullPointerException e){
      this.claimant = new Collaborator("Unclaimed", "Unclaimed", "Unclaimed", "Unclaimed");
    }
    timeline = new LinkedList<>();
    List<Event> projectTimeline = project.getTimeline();
    for(Event event : projectTimeline){
      if(event.getTaskNumber() == this.taskNumber){
        timeline.add(event);
      } 
    }
  }

  public Task(int number, String title, String description, String dateAdded, List<Event> timeline, 
		State state, Collaborator collaborator){
    this.taskNumber = number;
	this.title = title;
    this.description = description; 
    this.dateAdded = LocalDate.parse(dateAdded);
    this.timeline = timeline;
    this.state = state;
    this.claimant = collaborator;
  }


  
  /**
  * Gets the number of the task
  * @return the task number
  */
  public int getTaskNumber(){
    return taskNumber;
  }

  /**
  * Gets the title of the task
  * @return the title of the task 
  */
  public String getTitle(){
    return title;
  }
  
  /**
  * Gets the description of the task
  * @return the description of the task 
  */
  public String getDescription(){
    return description;
  }

  /**
  * Gets the date the task was created
  * @return the date the task was created 
  */
  public LocalDate getDateCreated(){
    return dateAdded;
  }

  /**
  * Gets the current state of the task (Ready, In Progress, Testing, etc...)
  * @return the state of the task
  */
  public State getState(){
    return state;
  }
  
  /**
  * Gets the number of incorrect moves the task has made
  * @return number of incorrect moves the task has made
  */ 
  public int getNumIncorrectMoves(){
    int count = 0;
    boolean completed = false;
    for(Event event : timeline){
      if (completed || event.checkValid(claimant) == false){
        count++;
      }
      if (event.getEndState().compareTo(State.COMPLETE) ==  0){
        completed = true;
      }
    } 
    return count;
  }
  
  /**
  * Gets the number of incorrect moves an individual has made
  * @return number of incorrect moves an individual has made
  */ 
  public int getNumIncorrectMovesCollaborator(Collaborator collaborator){
    int count = 0;
    boolean completed = false;
    for(Event event : timeline){
      if ((completed || event.checkValid(claimant) == false) && (collaborator == event.getInitiator())){
        count++;
      }
      if (event.getEndState().compareTo(State.COMPLETE) ==  0){
        completed = true;
      }
    } 
    return count;
  } 

  /**
  * Gets the collaborator who has claimed the task
  * @return the collaborator if the task has been claimed or null if not
  */
  public Collaborator getClaimant(){
    return claimant;
  }

  /**
  * Checks whether the task has been claimed
  * @return boolean indicating whether the task has been claimed
  */
  public boolean claimed(){
    if(claimant == null){
      return false;
    } else {
      return true;
    }
  }
 
  /**
  * Checks whether the task has been completed
  * @return boolean indicating whether the task has been completed
  */
  public boolean completed(){
    if(state.compareTo(State.COMPLETE) == 0){
      return true;
    }
    return false;
  }

  /**
  * Gets the first date the task was moved into complete
  * @return the completion date of the task
  */
  public LocalDate getCompletionDate(){
    for(Event event : timeline){
      if(event.getEndState().compareTo(State.COMPLETE) == 0){
        return event.getDate();
      }
    }
    return null;
  }

  /**
  * Gets all events associated with the task
  * @return the events associated with the task
  */
  public List<Event> getTimeline(){
    return timeline;
  }

  /**
  * Gets all events associated with the task on a given day
  * @param date is the date of interest for the information
  * @return the events associated with the task on a given day
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

  public LinkedHashMap<String, String> toSimpleMap(){
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put("Title", getTitle());
    map.put("Claiment", getClaimant().getFirstName());
    map.put("State", getState().toString());
    return map;
  }
}
