package main.java.memoranda.model;

import main.java.memoranda.api.SprintData;
import main.java.memoranda.api.UserStoryData;

import java.time.LocalDate;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.time.Period;

/**
* Defines the Sprint class, Taiga King's internal representation of a Taiga Sprint
*  
* @author Lebkuchen Team
* @version 0.9
*/
public class Sprint {
  private String name;
  private LocalDate startDate;
  private LocalDate endDate;
  private List<Event> timeline;
  private HashMap<Integer, UserStory> stories;
  private HashMap<String, Collaborator> collaborators;
  private String burndown;
  private Project project;

  public Sprint(SprintData sprintData, HashMap<String, Collaborator> collaborators, Project project){

    this.name = sprintData.getName();
    this.startDate = LocalDate.parse(sprintData.getEstimated_start().substring(0,10));
    this.endDate = LocalDate.parse(sprintData.getEstimated_finish().substring(0,10));
    this.stories = new HashMap<>();
    this.project = project;

    for(UserStoryData userStory: sprintData.getUser_stories()){
      stories.put(Integer.parseInt(userStory.getRef()), new UserStory(userStory, collaborators, project));
    }
    timeline = new LinkedList<>();
    for(UserStory story : stories.values()){
      List<Event> storyTimeline = story.getTimeline();
      for(Event event : storyTimeline){
        timeline.add(event);
      }
    }

    this.collaborators = new HashMap<>();
    for(UserStory story : stories.values()){
      for(String name : story.getCollaboratorNames()){
        this.collaborators.put(name, collaborators.get(name));
      }
    }
  }


  
  public Sprint(String name, String startDate, String endDate, List<Event> timeline, HashMap<Integer, 
		UserStory> stories, HashMap<String, Collaborator> collaborators, String burndown){
	this.name = name;
    this.startDate = LocalDate.parse(startDate);
    this.endDate = LocalDate.parse(endDate);
    this.timeline = timeline;
    this.stories = stories;
    this.collaborators = collaborators;
    this.burndown = burndown;
  }

  /**
  * Gets the name of the sprint
  * @return the name of the sprint
  */
  public String getName(){
    return name;
  }

  /**
  * Gets the start date of the sprint
  * @return the start date of the sprint
  */
  public LocalDate getStartDate(){
    return startDate;
  }

  /**
borators.keySet()  * Gets the end date of the sprint
  * @return the end date of the sprint
  */
  public LocalDate getEndDate(){
    return endDate;
  }
  
  /**
  * Get the duration of the sprint in days
  * @return the duration of the sprint
  */
  public int getDuration(){
    if(startDate == null || endDate == null){
      return -1;
    }
    Period period = Period.between(startDate, endDate);
    return period.getDays();
  }

  /**
  * Determines whether the sprint has completed or not (end date is past)
  * @return the end date of the sprint
  */
  public boolean isFinished(){
    if(LocalDate.now().compareTo(endDate) > 0){
      return true;
    }
    return false;
  }

  /**
  * Gets the total number of user stories associated with the sprint
  * @return the total number of user stories associated with the sprint
  */
  public int getNumberUserStories(){
    return stories.size();
  }

  /**
  * Gets the total number of points achievable for the sprint
  * @return the total number of points achievable for the sprint
  */
  public int getPointsPossible(){
    int count = 0;
    for(UserStory story : stories.values()){
      count += story.getNumPoints();
    }
    return count;
  }

  /**
  * Gets the total number of points earned for the sprint
  * @return the total number of points earned for the sprint
  */
  public int getPointsEarned(){
    int count = 0; 
    for(UserStory story : stories.values()){
      if(story.isFinished()){
        count += story.getNumPoints();
      }
    }
    return count;
  }

  /**
  * Gets the number of open tasks associated with the sprint
  * @return the number of open tasks associated with the sprint
  */
  public int getNumOpenTasks(){
    int count = 0;
    for(UserStory story : stories.values()){
      for(int key : story.getTaskNumbers()){
        if(!story.getTask(key).completed()){
          count++;
        }
      }
    }
    return count;
  }

  /**
  * Gets the number of closed tasks associated with the sprint
  * @return the number of closed tasks associated with the sprint
  */
  public int getNumClosedTasks(){
    int count = 0;
    for(UserStory story : stories.values()){
      for(int key : story.getTaskNumbers()){
        if(story.getTask(key).completed()){
          count++;
        }
      }
    }
    return count;
  }

  /**
  * Gets the number of tasks associated with the sprint
  * @return the number of tasks associated with the sprint
  */
  public int getNumTotalTasks(){
    return getNumOpenTasks() + getNumClosedTasks();
  }

  /**
  * Gets the percentage of tasks that are completed
  * @return the percentage of tasks that are completed
  */
  public int getPercentCompletionTasks(){
    double percent = (double) getNumClosedTasks() / (double) getNumTotalTasks();
    System.out.println(percent);
    return (int) (percent * 100);
  }

  /**
  * Gets the percentage of points that have been earned
  * @return the percentage of points that have been earned
  */
  public int getPercentEarnedPoints(){
    double percent = (double) getPointsEarned() / (double) getPointsPossible();
    return (int) (percent * 100);
  }

  /**
  * Determines the number of tasks completed on a particular day of the sprint
  * @param date is the date of interest for the information
  * @return the number of tasks completed on a particular day of the sprint
  */
  public int getDaysTaskComplete(String date){
   int count = 0;
    for(UserStory story : stories.values()){
      for(int key : story.getTaskNumbers()){
        if(story.getTask(key).completed() == false){
          continue;
        }
        if(story.getTask(key).getCompletionDate().compareTo(LocalDate.parse(date)) == 0){
          count++;
        }
      }
    }
    return count;  
  }
  /**
  * Determines the average number of tasks completed per day over the course of the sprint
  * @return the average number of tasks completed per day over the course of the sprint
  */
  public double getTaskRateOverall(){
    double count = 0;
    for(UserStory story : stories.values()){
      for(int key : story.getTaskNumbers()){
        if(story.getTask(key).completed() == false) {
          continue;
        }
        count++;
      }
    }
    return count / getDuration();
  }

  /**
  * Gets the burndown chart associated with the sprint
  * @return the burndown chart associated with the sprint
  * NOTE: this will probably just be a link to the image hosted on Taiga so I am leaving the
  * return type as a string for now
  */
  public String getBurnDown(){
    return burndown;
  }

  /**
  * Calculates a rating for a team's performance on the sprint;
  * based on their task completion, points earned, and incorrect moves
  * @return a double value rating a team's performance on the sprint
  */
  public double getSprintRating( ){
    double score = 10;
    if(getNumClosedTasks() < getNumTotalTasks()){
      score -= (getNumTotalTasks() - getNumClosedTasks()) * .1;
    }
    if(getPointsEarned() < getPointsPossible()) {
      score -= (getPointsPossible() - getPointsEarned()) * .1;
    }
    score -=  getNumIncorrectMoves() * .1;
    return score; 
  }

  /**
  * Gets the number of times tasks were moved incorrectly 
  * @return the number of times tasks and user stories were moved incorrectly
  */
  public int getNumIncorrectMoves(){
    int count = 0;
    for(UserStory story : stories.values()){
      count += story.getNumIncorrectMoves();
    } 
    return count;
  }

  /**
  * Gets the number of times a collaborator has moved a task incorrectly
  * @param collaborator is the collaborator of interest 
  * @return the number of times a collaborator has moved a task incorrectly
  */
  public int getNumIncorrectMovesCollaborator(Collaborator collaborator){
    int count = 0;
    for(UserStory story : stories.values()){
      count += story.getNumIncorrectMovesCollaborator(collaborator);
    }
    return count; 
  }
  
  /**
  * Gets all events associated with a particular sprint
  * @return a list of events associated with a particular sprint
  */
  public List<Event> getTimeline(){
    return timeline;
  }

  /**
  * Gets all events associated with a particular sprint on a given day
  * @param date is the date of interest for the information
  * @return a list of events associated with a particular sprint on a given day
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
  * Gets all events associated with a particular collaborator
  * @param collaborator is the username of the collaborator of interest
  * @return a list of all events associated witha particular collaborator
  */
  public List<Event> getCollaboratorTimeline(Collaborator collaborator){
    LinkedList<Event> collaboratorTimeline = new LinkedList<>();
    for(Event event : timeline){
      if(event.getInitiator() == collaborator){
        collaboratorTimeline.add(event);
      }
    }
    return collaboratorTimeline;
  }

  /**
  * Gets the numbers of all user stories associated with the sprint 
  * @return a list of all the numbers of user stories associated with the sprint
  */
  public List<Integer> getUserStoryNumbers(){
    return new LinkedList<Integer>(stories.keySet());
  }

  /** 
  * Gets the numbers of all user stories in the sprint a collaborator participated in
  * @param username is the username of the collaborator of interest
  * @return a list of all the numbers of user stories associated with a particular collaborator 
  */
  public List<Integer> getCollaboratorUserStoryNumbers(String username){
    LinkedList<Integer> numbers = new LinkedList<>();
    for(UserStory story : stories.values()){
      if(story.getCollaborator(username) != null){
        numbers.add(story.getUserStoryNumber()); 
      }
    }
    return numbers;
  }

  /**
  * Gets a particular user story based on number
  * @param usNumber is the number associated with a particular user story
  */
  public UserStory getUserStory(int usNumber){
    return stories.get(usNumber);
  }

  public HashMap<Integer, UserStory> getStories() {
    return stories;
  }

  /**
  * Gets the usernames of all collaborators associated with the sprint
  * @return a list of the usernames of all collaborators associated with the sprint
  */
  public List<String> getCollaboratorFull_Name(){
    return new LinkedList<String>(collaborators.keySet());
  }
  
  /**
  * Gets a particular collaborator based on username
  * @param username is the username of a collaborator associated with the sprint
  * @return the collaborator associated with a particular username
  */
  public Collaborator getCollaborator(String username){
    return collaborators.get(username);
  }

  public void setStories(HashMap<Integer, UserStory> stories) {
    this.stories = stories;
  }

  /**
  * Gets a simple map representation of the sprint
  * @return a map representation of the sprint
  */
  public LinkedHashMap<String, String> toSimpleMap(){
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put("Title", getName());
    map.put("Start Date", getStartDate().toString());
    map.put("End Date", getEndDate().toString());
    map.put("# US", "" + getNumberUserStories());
    map.put("# Tasks", "" + getNumTotalTasks());
    return map;

  }
  
  /**
  * Gets a detailed map representation of the sprint
  * @return a map representation of the sprint
  */
  public LinkedHashMap<String, String> toAdvancedMap(){
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put("Title", getName());
    map.put("Start Date", getStartDate().toString());
    map.put("End Date", getEndDate().toString());
    map.put("# US", "" + getNumberUserStories());
    map.put("# Tasks", "" + getNumTotalTasks());
    map.put("# Points Possible", "" + getPointsPossible());
    map.put("# Points Earned", "" + getPointsEarned());
    map.put("Point %", "" + getPercentEarnedPoints() + "%");
    map.put("Open Tasks", "" + getNumOpenTasks());
    map.put("Closed Tasks", "" + getNumClosedTasks());
    map.put("Task Completion %", "" + getPercentCompletionTasks() + "%");
    map.put("Tasks/Day", "" + getTaskRateOverall());
    map.put("Incorrect Task Moves", "" + getNumIncorrectMoves());
    map.put("Sprint Rating", "" + getSprintRating());
    return map;
  } 
}
