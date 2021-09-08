

import java.time.LocalDate;
import java.util.List;
import java.util.LinkedList;

import main.java.memoranda.model.Collaborator;
import main.java.memoranda.model.Event;
import main.java.memoranda.model.State;
import main.java.memoranda.model.Task;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaskTest {
  Collaborator goodGuy;
  Collaborator badGuy;
  Event goodEvent;
  Event anotherGoodEvent;
  Event yetAnotherGoodEvent;
  Event badEvent;
  Event anotherBadEvent;
  List<Event> timeline;
  Task task;
  Task unclaimedTask;
  Task completeTask;

  @Before
  public void setUp(){
    goodGuy = new Collaborator("goodguy", "good", "guy", "goodicon");
    badGuy = new Collaborator("badguy", "bad", "guy", "badicon");
    goodEvent = new Event(1, goodGuy, "2020-04-12", "Ready -> In Progress", State.READY, State.IN_PROGRESS);
    anotherGoodEvent = new Event(1, goodGuy, "2020-04-12", "In Progress -> Testing", State.IN_PROGRESS, State.TESTING);
    yetAnotherGoodEvent = new Event(1, goodGuy, "2020-04-13", "Testing -> Complete", State.TESTING, State.COMPLETE);
    badEvent = new Event(1, badGuy, "2020-04-14", "Complete -> Testing", State.COMPLETE, State.TESTING);
    anotherBadEvent = new Event(1, badGuy, "2020-04-14", "Testing -> Ready", State.TESTING, State.READY);
    timeline = new LinkedList<>();
    timeline.add(goodEvent);
    timeline.add(anotherGoodEvent);
    timeline.add(yetAnotherGoodEvent);
    timeline.add(badEvent);
    timeline.add(anotherBadEvent);
    task = new Task(1, "Doing some important things", "This is a description", "2020-04-12", timeline, State.READY, goodGuy);
    unclaimedTask = new Task(2, "Doing other things", "Another description", "2020-04-12", new LinkedList<Event>(), State.READY, null);
    completeTask = new Task(3, "Doing more things", "Description", "2020-04-12", new LinkedList<Event>(), State.COMPLETE, goodGuy);
  }

  @Test
  public void testGetTitle(){
    assertTrue(task.getTitle().equals("Doing some important things"));
  }
  
  @Test
  public void testGetDescription(){
    assertTrue(task.getDescription().equals("This is a description"));
  }

  @Test
  public void testGetDateCreated(){
    assertTrue(task.getDateCreated().equals(LocalDate.parse("2020-04-12")));
  }
  
  @Test
  public void testGetState(){
    assertTrue(task.getState().compareTo(State.READY) == 0);
  }

  @Test
  public void testGetNumIncorrectMoves(){
    assertTrue(task.getNumIncorrectMoves() == 2);
  }

  @Test
  public void testGetNumIncorrectMovesCollaborator(){
    assertTrue(task.getNumIncorrectMovesCollaborator(badGuy) == 2);
    assertTrue(task.getNumIncorrectMovesCollaborator(goodGuy) == 0);
  }
  
  @Test
  public void testGetClaimant(){
    assertTrue(task.getClaimant() == goodGuy);
  }

  @Test
  public void testClaimedTrue(){
    assertTrue(task.claimed());
  }
  
  @Test
  public void testClaimedNotTrue(){
    assertFalse(unclaimedTask.claimed());
  }

  @Test
  public void testGetCompletionDate(){
    assertTrue(task.getCompletionDate().equals(LocalDate.parse("2020-04-13")));
  }

  @Test
  public void taskCompletedTrue(){
    assertTrue(completeTask.completed());
  }

  @Test
  public void taskCompletedFalse(){
    assertFalse(unclaimedTask.completed());
  }

  @Test
  public void testGetTimeLine(){
    assertTrue(task.getTimeline() == timeline); 
  }

  @Test
  public void testGetDaysTimelineValid(){
    int count = 0;
    for(Event event : task.getDaysTimeline("2020-04-12")){
      assertTrue(event.getDate().equals(LocalDate.parse("2020-04-12")));
      count++;
    }
    assertTrue(count == 2); 
  } 
  
  @Test
  public void testGetDaysTimelineInvalid(){
    int count = 0;
    for(Event event : task.getDaysTimeline("2020-04-12")){
      assertFalse(false);
    }
    assertTrue(count == 0);
  }
}
