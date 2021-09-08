
import main.java.memoranda.model.State;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StateTest {
  State state;
  
  @Before
  public void setUp(){
    state = State.READY;
  }
  
  @Test
  public void testToString(){
    assertTrue(state.toString().equals("Ready"));
  }
}
