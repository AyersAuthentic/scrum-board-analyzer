

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

import main.java.memoranda.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserStoryTest {
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
  } 

  @Test
  public void testGetUserStoryNumber(){
    assertEquals(userStory.getUserStoryNumber(), 1);
  }

  @Test
  public void testGetTitle(){
    assertEquals(userStory.getTitle(), "Doing lots of things");
  }
 
  @Test
  public void testGetDescription(){
    assertEquals(userStory.getDescription(), "A big description");
  }

  @Test
  public void testGetDateAddedProduct(){
    assertEquals(userStory.getDateAddedProduct(), LocalDate.parse("2020-04-11"));
  }

  @Test
  public void testGetDateAddedSprint(){
    assertEquals(userStory.getDateAddedSprint(), LocalDate.parse("2020-04-12"));
  } 

  @Test
  public void testGetCompletionDate(){
    assertTrue(userStory.getCompletionDate() == null);
  }

  @Test
  public void testIsFinished(){
    assertEquals(userStory.isFinished(), false);
  }

  @Test
  public void testGetTaskNumbers(){
    List<Integer> numbers = userStory.getTaskNumbers();
    HashSet<Integer> set = new HashSet<>(numbers);
    if(set.contains(1) && set.contains(2) && set.contains(3) && set.size() == 3){
      assert true;
    } else {
      assert false;
    }
  }

  @Test
  public void testGetTask(){
    assertEquals(goodGuyTask, userStory.getTask(1));
  }

  @Test
  public void testGetTimeline(){
    assertEquals(userStory.getTimeline(), wholeTimeline);
  }

  @Test
  public void testGetDaysTimeline(){
    assertTrue(userStory.getDaysTimeline("2020-04-12").size() == 2);
  }

  @Test
  public void testGetNumTasksInReady(){
    assertEquals(userStory.getNumTasksInReady(), 1);
  }
     
  @Test
  public void testGetNumTasksInComplete(){
    assertEquals(userStory.getNumTasksInComplete(), 1);
  }
 
  @Test
  public void testGetNumTasksInTest(){
    assertEquals(userStory.getNumTasksInTest(), 1);
  }

  @Test
  public void testGetNumTotalTasks(){
    assertEquals(userStory.getNumTotalTasks(), 3);
  }
  
  @Test
  public void testGetNumPoints(){
    assertEquals(userStory.getNumPoints(), 3); 
  }

  @Test
  public void testGetNumIncorrectMoves(){
    assertTrue(userStory.getNumIncorrectMoves() == 3);
  }

  @Test
  public void testGetNumIncorrectMovesCollaborator(){
    assertTrue(userStory.getNumIncorrectMovesCollaborator(badGuy) == 3);
    assertTrue(userStory.getNumIncorrectMovesCollaborator(goodGuy) == 0);
  }

  @Test
  public void testGetCollaboratorNames(){
    List<String> usernames = userStory.getCollaboratorNames();
    HashSet<String> set = new HashSet<>(usernames);
    if(set.contains("goodguy") && set.contains("badguy") && set.size() == 2){
      assert true;
    } else {
      assert false;
    }
  }

  @Test
  public void testGetCollaborator(){
    assertEquals(userStory.getCollaborator("badguy"), badGuy); 
  }
 

}
