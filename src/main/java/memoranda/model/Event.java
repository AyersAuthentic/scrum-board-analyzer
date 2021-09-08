package main.java.memoranda.model;

import com.sun.security.auth.NTUserPrincipal;
import main.java.memoranda.api.TimeLine;

import java.time.LocalDate;
import java.util.List;

/**
* Defines the Event class, Taiga King's internal representation for Taiga task status/state changes
*  
* @author Lebkuchen Team
* @version 0.9
*/

public class Event {
  private int taskNumber;
  private Collaborator initiator;
  private LocalDate date;
  private String description;
  private String userStorySubject;
  private String taskSubject;
  private State startState;
  private State endState;


  public Event(TimeLine timeLine, Collaborator initiator){
    try{
      this.taskNumber = Integer.parseInt(timeLine.getData().getTask().getRef());
    }catch(NullPointerException e){
      this.taskNumber = 0;
    }

    this.date = LocalDate.parse(timeLine.getCreated().substring(0, 10));
    this.description = timeLine.getEvent_type();
    this.initiator = initiator;
//    try{
//
//      this.userStorySubject = timeLine.getData().getUserstory().getSubject();
//
//    }catch(NullPointerException e){
//      this.userStorySubject = "";
//    }
//    try{
//      this.taskSubject = timeLine.getData().getTask().getSubject();
//    }catch (NullPointerException e){
//      this.taskSubject = null;
//    }

    if(timeLine.getData().getValues_diff() != null){
      if(timeLine.getData().getValues_diff().getStatus() != null){
        this.startState = State.stringToState(timeLine.getData().getValues_diff().getStatus()[0]);
        this.endState = State.stringToState(timeLine.getData().getValues_diff().getStatus()[1]);
      }
    }

  }

  public Event(int taskNumber, Collaborator collaborator, String date, String description, 
  		State startState, State endState){
    this.taskNumber = taskNumber;
    this.initiator = collaborator;
    this.date = LocalDate.parse(date);
    this.description = description;
    this.startState = startState;
    this.endState = endState;
  }

  /**
  * Gets the number of the task the event is associated with
  * @return the number of the task the event is associated with
  */
  public int getTaskNumber(){
    return taskNumber;
  }
 
  /**
  * Gets the collaborator who performed the event
  * @return the collaborator who performed the event 
  */
  public Collaborator getInitiator(){
    return initiator;
  }

  /**
  * Gets the date the event occured
  * @return the date the event occured
  */
  public LocalDate getDate(){
    return date;
  }
  
  /**
  * Gets the text description of the event
  * @return the text description of the event 
  */
  public String getDescription(){
    return description;
  }

  /**
  * Checks whether the state change was valid (the Task/US moved in the correct direction)
  * @return boolean indicating whether the event was valid
  */
  public boolean checkValid(Collaborator taskOwner){
    if(taskOwner != initiator){
      return false;
    } else if(difference() == 1){
      return true;
    } else if((difference() == -1) && (startState.compareTo(State.TESTING) == 0)){
      return true;
    } else return false;
  }

  public int difference(){
    return endState.ordinal() - startState.ordinal();
  }

  /**
  * Gets the beginning state of the event as string
  * @return the beginning state of the event
  */
  public String getStartStateString(){
    return startState.toString(); 
  }

  /**
  * Gets the end state of the event as string
  * @return the end state of the event
  */
  public String getEndStateString(){
    return endState.toString();
  }

  /**
  *  Gets the beginning state of the event
  *  @return the beginning state of the event
  */
  public State getStartState(){
    return startState;
  }
  
  /**
  * Gets the end state of the event
  * @return the end state of the event
  */
  public State getEndState(){
    return endState;
  }    
}
