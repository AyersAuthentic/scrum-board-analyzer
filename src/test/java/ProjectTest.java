import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

import main.java.memoranda.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectTest {
  Collaborator goodGuy;
  Collaborator badGuy;
  Event goodEvent;
  Event anotherGoodEvent;
  Event yetAnotherGoodEvent;
  Event badEvent;
  Event anotherBadEvent;
  Event yetAnotherBadEvent;
  List<Event> goodGuyTimeline;
  List<Event> badGuyTimeline;
  List<Event> wholeTimeline;
  Task goodGuyTask;
  Task unclaimedTask;
  Task badGuyTask;
  HashMap<Integer, Task> tasks;
  HashMap<String, Collaborator> collaborators;
  UserStory userStory;
  HashMap<Integer, UserStory> stories;
  Sprint sprint1;
  Sprint sprint2;
  HashMap<String, Sprint> sprints;
  Project project;
  
  @Before
  public void setUp(){
    goodGuy = new Collaborator("goodguy", "good", "guy", "goodicon");
    badGuy = new Collaborator("badguy", "bad", "guy", "badicon");
    collaborators = new HashMap<>();
    collaborators.put("goodguy", goodGuy);
    collaborators.put("badguy", badGuy);

    goodGuyTimeline = new LinkedList<>();
    goodEvent = new Event(1, goodGuy, "2020-04-12", "Ready -> In Progress", State.READY, State.IN_PROGRESS);
    anotherGoodEvent = new Event(1, goodGuy, "2020-04-12", "In Progress -> Testing", State.IN_PROGRESS, State.TESTING);
    yetAnotherGoodEvent = new Event(1, goodGuy, "2020-04-13", "Testing -> Complete", State.TESTING, State.COMPLETE);
    badEvent = new Event(1, badGuy, "2020-04-14", "Complete -> Testing", State.COMPLETE, State.TESTING);
    anotherBadEvent = new Event(1, badGuy, "2020-04-14", "Testing -> Ready", State.TESTING, State.READY);
    goodGuyTimeline.add(goodEvent);
    goodGuyTimeline.add(anotherGoodEvent);
    goodGuyTimeline.add(yetAnotherGoodEvent);
    goodGuyTimeline.add(badEvent);
    goodGuyTimeline.add(anotherBadEvent);
    goodGuyTask = new Task(1, "Doing some important things", "This is a description", "2020-04-12", goodGuyTimeline, State.READY, goodGuy);

    unclaimedTask = new Task(2, "Doing other things", "Another description", "2020-04-12", new LinkedList<Event>(), State.TESTING, null);
    

	badGuyTimeline = new LinkedList<>();
    yetAnotherBadEvent = new Event(3, badGuy, "2020-04-15", "Ready -> Complete", State.READY, State.COMPLETE);
    badGuyTimeline = new LinkedList<>();
    badGuyTimeline.add(yetAnotherBadEvent);
    badGuyTask = new Task(3, "Doing bad things", "This description", "2020-04-13", badGuyTimeline, State.COMPLETE, null);
	wholeTimeline = new LinkedList<>(); 
    for(Event event : goodGuyTimeline){
      wholeTimeline.add(event);
    }
    for(Event event : badGuyTimeline){
      wholeTimeline.add(event);
    }
    
    tasks = new HashMap<>();
    tasks.put(1, goodGuyTask);
    tasks.put(2, unclaimedTask);
    tasks.put(3, badGuyTask);

    userStory = new UserStory("Doing lots of things", "A big description", "2020-04-11", "2020-04-12", 1, tasks, collaborators, wholeTimeline, 3);
    stories = new HashMap<>();
    stories.put(1, userStory);	 
    sprint1 = new Sprint("Sprint", "2020-04-12", "2020-04-15", wholeTimeline, stories, collaborators, "burndownurl"); 
    sprint2 = new Sprint("EmptySprint", "2020-04-12", "2020-04-15", new LinkedList<Event>(), new HashMap<Integer, UserStory>(), new HashMap<String, Collaborator>(), "anotherburndownurl");
    sprints = new HashMap<String, Sprint>();
    sprints.put("Sprint", sprint1);
    sprints.put("EmptySprint", sprint2);
    project = new Project("Project", "This is a project", 1, wholeTimeline, collaborators, sprints);
  }

  @Test
  public void testGetName(){
    assertEquals(project.getName(), "Project");
  }
  
  @Test
  public void testGetDescription(){
    assertEquals(project.getDescription(), "This is a project"); 
  }
  
  @Test
  public void testGetID(){
    assertEquals(project.getID(), 1);
  }

  @Test
  public void testGetTimeline(){
    assertEquals(project.getTimeline().size(), 6);
  }
  
  @Test 
  public void testGetDaysTimeline(){
    assertEquals(project.getDaysTimeline("2020-04-14").size(), 2);
  }

  @Test
  public void testGetCollaboratorNames(){
    assertEquals(project.getCollaboratorNames().size(), 2);
  }

  @Test
  public void testGetCollaborator(){
    assertTrue(project.getCollaborator("goodguy") != null);
  }   

  @Test
  public void testGetSprintNames(){
    assertEquals(project.getSprintNames().size(), 2);
  }

  @Test
  public void testGetSprint(){
    assertEquals(project.getSprint("Sprint"), sprint1);
  }
}
