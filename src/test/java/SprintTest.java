

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

import main.java.memoranda.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SprintTest {

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
  Sprint sprint;

  @Before
  public void setUp() {
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
    for (Event event : goodGuyTimeline) {
      wholeTimeline.add(event);
    }
    for (Event event : badGuyTimeline) {
      wholeTimeline.add(event);
    }

    tasks = new HashMap<>();
    tasks.put(1, goodGuyTask);
    tasks.put(2, unclaimedTask);
    tasks.put(3, badGuyTask);

    userStory = new UserStory("Doing lots of things", "A big description", "2020-04-11", "2020-04-12", 1, tasks, collaborators, wholeTimeline, 3);
    stories = new HashMap<>();
    stories.put(1, userStory);
    sprint = new Sprint("Sprint", "2020-04-12", "2020-04-15", wholeTimeline, stories, collaborators, "burndownurl");
  }

  @Test
  public void testGetName() {
    assertEquals(sprint.getName(), "Sprint");
  }

  @Test
  public void testGetStartDate() {
    assertTrue(sprint.getStartDate().compareTo(LocalDate.parse("2020-04-12")) == 0);
  }

  @Test
  public void testGetEndDate() {
    assertTrue(sprint.getEndDate().compareTo(LocalDate.parse("2020-04-15")) == 0);
  }

  @Test
  public void testGetDuration() {
    assertEquals(sprint.getDuration(), 3);
  }

  @Test
  public void testIsFinished() {
    assertTrue(sprint.isFinished());
  }

  @Test
  public void testGetNumberUserStories() {
    assertEquals(sprint.getNumberUserStories(), 1);
  }

  @Test
  public void testGetPointsPossible() {
    assertEquals(sprint.getPointsPossible(), 3);
  }

  @Test
  public void testGetPointsEarned() {
    assertEquals(sprint.getPointsEarned(), 0);
  }

  @Test
  public void testGetNumOpenTasks() {
    assertEquals(sprint.getNumOpenTasks(), 2);
  }

  @Test
  public void testGetNumClosedTasks() {
    assertEquals(sprint.getNumClosedTasks(), 1);
  }

  @Test
  public void testGetNumTotalTasks() {
    assertEquals(sprint.getNumTotalTasks(), 3);
  }

  @Test
  public void testGetPercentCompletionTasks() {
    assertEquals(sprint.getPercentCompletionTasks(), 33);
  }

  @Test
  public void testGetPercentCPointsEarned() {
    assertEquals(sprint.getPercentEarnedPoints(), 0);
  }

  @Test
  public void testGetTimeline() {
    assertEquals(sprint.getTimeline().size(), 6);
  }

  @Test
  public void testGetDaysTimeline() {
    assertTrue(sprint.getDaysTimeline("2020-04-12").size() == 2);
  }

  @Test
  public void testGetDaysTaskComplete() {
    assertEquals(sprint.getDaysTaskComplete("2020-04-13"), 0);
  }

  @Test
  public void testGetTaskRateOverall() {
    assertEquals(sprint.getTaskRateOverall(), .33, .1);
  }

  @Test
  public void testGetBurndown() {
    assertEquals(sprint.getBurnDown(), "burndownurl");
  }

  @Test
  public void testGetSprintRating() {
    assertEquals(sprint.getSprintRating(), 9.2, .1);

  }

  @Test
  public void testGetNumIncorrectMoves() {
    assertEquals(sprint.getNumIncorrectMoves(), 3);
  }

  @Test
  public void testGetNumIncorrectMovesCollaborator() {
    assertEquals(sprint.getNumIncorrectMovesCollaborator(badGuy), 3);
    assertEquals(sprint.getNumIncorrectMovesCollaborator(goodGuy), 0);
  }

  @Test
  public void testGetCollaboratorTimeline() {
    assertEquals(sprint.getCollaboratorTimeline(badGuy).size(), 3);
  }

  @Test
  public void testGetUserStoryNumbers() {
    assertEquals(sprint.getUserStoryNumbers().size(), 1);
  }

  @Test
  public void testGetCollaboratorUserStoryNumbers() {
    assertEquals(sprint.getCollaboratorUserStoryNumbers("badguy").size(), 1);
  }

  @Test
  public void testGetUserStory() {
    assertTrue(sprint.getUserStory(1) != null);
  }

  @Test
  public void testGetCollaboratorNames() {
    List<String> usernames = userStory.getCollaboratorNames();
    HashSet<String> set = new HashSet<>(usernames);
    if (set.contains("goodguy") && set.contains("badguy") && set.size() == 2) {
      assert true;
    } else {
      assert false;
    }
  }

  @Test
  public void testGetCollaborator() {
    assertEquals(userStory.getCollaborator("badguy"), badGuy);
  }
}