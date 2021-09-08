package main.java.memoranda.ui;

import main.java.memoranda.model.*;
import java.util.LinkedList;

public class SprintCard extends Card {
  Sprint sprint;
  public SprintCard(Sprint sprint){
    super(sprint.getName(), sprint.toSimpleMap());
    this.sprint = sprint;
  }

  public Sprint getSprint(){
    return sprint;
  } 
}
