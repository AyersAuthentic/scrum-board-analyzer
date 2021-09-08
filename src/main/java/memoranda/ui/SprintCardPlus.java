package main.java.memoranda.ui;

import main.java.memoranda.model.*;
import java.util.LinkedList;

public class SprintCardPlus extends CardPlus {
  Sprint sprint;
  public SprintCardPlus(Sprint sprint){
    super(sprint.getName(), sprint.toAdvancedMap());
    this.sprint = sprint;
  }

  public Sprint getSprint(){
    return sprint;
  } 
}
