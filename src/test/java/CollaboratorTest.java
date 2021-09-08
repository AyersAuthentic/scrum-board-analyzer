

import main.java.memoranda.model.Collaborator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollaboratorTest {
  Collaborator collaborator;
  
  @Before
  public void setUp(){
    collaborator = new Collaborator("username", "firstname", "lastname", "iconurl");
  }

  @Test
  public void testGetUsername(){
    assertTrue(collaborator.getUsername().equals("username"));
  }

  @Test
  public void testGetFirstName(){
    assertTrue(collaborator.getFirstName().equals("firstname"));
  }

  @Test
  public void testGetLastName(){
    assertTrue(collaborator.getLastName().equals("lastname"));
  }

  @Test
  public void getFullName(){
    assertTrue(collaborator.getFullName().equals("firstname lastname"));
  }

  @Test
  public void testGetIcon(){
    assertTrue(collaborator.getIcon().equals("iconurl"));
  }
}
