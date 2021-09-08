

import java.time.LocalDate;

import main.java.memoranda.model.Collaborator;
import main.java.memoranda.model.Event;
import main.java.memoranda.model.State;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EventTest {
  Event badEvent;
  Event goodEvent;
  Event otherBadEvent;
  Event otherGoodEvent;
  Event anotherBadEvent;
  Collaborator collaborator;
  Collaborator otherCollaborator;
  
  @Before
  public void setUp(){
    collaborator = new Collaborator("firstcollaborator", "first", "collaborator", "firsticon");
    otherCollaborator = new Collaborator("secondcollaborator", "second", "collaborator", "secondcollaborator");

    badEvent = new Event(1, collaborator, "2020-04-07", "This is a bad event", State.IN_PROGRESS, State.READY);
    goodEvent = new Event(2, collaborator, "2020-04-08", "This is a good event", State.READY, State.IN_PROGRESS);
    otherBadEvent = new Event(3, otherCollaborator, "2020-04-09", "This is a bad event", State.READY, State.IN_PROGRESS);
    otherGoodEvent = new Event(4, collaborator, "2020-04-09", "This is a good event", State.TESTING, State.IN_PROGRESS);
    anotherBadEvent = new Event(5, collaborator, "2020-04-08", "This is a good event", State.IN_PROGRESS, State.COMPLETE);

    
  }
 
  @Test
  public void testGetTaskNumber(){
    assertTrue(goodEvent.getTaskNumber() == 2);
  }

  @Test
  public void testCheckValidFalseMovement(){
    assertFalse(badEvent.checkValid(collaborator));
  }

  @Test
  public void testCheckValidFalseMovementForward(){
    assertFalse(anotherBadEvent.checkValid(collaborator));
  }

  @Test
  public void testCheckValidTrueMovementForward(){
    assertTrue(goodEvent.checkValid(collaborator));
  }

  @Test
  public void testcheckValidTrueMovementBack(){
    assertTrue(otherGoodEvent.checkValid(collaborator));
  }

  @Test
  public void testCheckValidFalseInitiator(){
    assertFalse(otherBadEvent.checkValid(collaborator));
  }

  @Test
  public void testGetInitiator(){
    assertTrue(goodEvent.getInitiator() == collaborator);
  }

  @Test
  public void testGetDate(){
    assertTrue(goodEvent.getDate().equals(LocalDate.parse("2020-04-08")));
  }

  @Test
  public void testGetDescription(){
    assertTrue(goodEvent.getDescription().equals("This is a good event"));
  }

  @Test
  public void testGetStartState(){
    assertTrue(goodEvent.getStartState().compareTo(State.READY) == 0);
  }

  @Test
  public void testGetEndState(){
    assertTrue(goodEvent.getEndState().compareTo(State.IN_PROGRESS) == 0);
  }
}
