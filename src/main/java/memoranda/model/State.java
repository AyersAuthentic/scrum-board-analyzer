package main.java.memoranda.model;

public enum State {
  READY("Ready"),
  IN_PROGRESS("In Progress"), 
  TESTING("Testing"), 
  COMPLETE("Complete"); 
  
  private final String textRepresentation;

  private State(String textRepresentation){
    this.textRepresentation = textRepresentation;
  }

  public static State stringToState(String status_name){

    if(status_name.equals("Ready for test")){
      return TESTING;
    }else if(status_name.equals("New")){
      return READY;
    }else if(status_name.equals("Closed")){
      return COMPLETE;
    }else if(status_name.equals("In progress")){
      return IN_PROGRESS;
    }else{
      return READY;
    }

  }
  
  @Override 
  public String toString(){
    return textRepresentation; 
  }
}



 


