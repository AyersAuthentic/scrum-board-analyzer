

import main.java.memoranda.api.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class sprintAPITest {

    private User user;
    private ProjectData[] projects;
    private SprintData[] sprints;
    String name2;
    String name1;
    String estStart2;
    String estStart1;
    String estFinish2;
    String estFinish1;

    @Before
    public void setUp() throws IOException {

        user = new User();
        projects = new ProjectData[50];
        sprints = new SprintData[50];


        user = UserAPI.loginUser(user, "kaayers1", "myPassword");
        projects = ProjectAPI.populateProjects(user, projects);
        sprints = SprintAPI.populateSprints(projects[0].getId(), sprints, user);





        name2 = "Sprint 2";
        name1 = "Sprint 1";

        estStart2 = "2020-04-09";
        estStart1 = "2020-03-28";

        estFinish2 = "2020-04-22";
        estFinish1 = "2020-04-05";

    }

    @Test
    public void testName2(){
        assertEquals(name2, sprints[0].getName());
    }

    @Test
    public void testName1(){
        assertEquals(name1, sprints[1].getName());
    }

    @Test
    public void testEstStart2(){
        assertEquals(estStart2, sprints[0].getEstimated_start());
    }

    @Test
    public void testEstStart1(){
        assertEquals(estStart1, sprints[1].getEstimated_start());
    }

    @Test
    public void setEstFinish2(){
        assertEquals(estFinish2, sprints[0].getEstimated_finish());
    }

    @Test
    public void setEstFinish1(){
        assertEquals(estFinish1, sprints[1].getEstimated_finish());
    }

}