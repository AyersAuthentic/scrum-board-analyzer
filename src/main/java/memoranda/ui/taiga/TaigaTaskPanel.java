package main.java.memoranda.ui.taiga;

import main.java.memoranda.model.DataStore;
import main.java.memoranda.model.Task;
import main.java.memoranda.model.UserStory;
import main.java.memoranda.ui.TaigaPanel;
import main.java.memoranda.ui.TaskToggle;
import main.java.memoranda.ui.data.PlaceholderDataStore;

import javax.swing.*;

public class TaigaTaskPanel extends JPanel {
    TaigaPanel nav;
    TaskToggle toggle;
    UserStory userStory;

    public TaigaTaskPanel(TaigaPanel nav, UserStory userStory){
        this.nav = nav;
        if(DataStore.projects == null){
            PlaceholderDataStore.initialize();
            this.userStory = PlaceholderDataStore.project.getSprint("This is a sprint").getUserStory(1);
            System.out.println(this.userStory.getTitle());
            jbInit();
            return;

        }

        this.userStory = userStory;
        jbInit();
        revalidate();
        repaint();
    }

    public void jbInit(){
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.add(createTitle());
        this.add(buildContent());
    }

    private JPanel createTitle(){
        JPanel title = new JPanel();
        JLabel userStoryName = new JLabel(userStory.getTitle());
        title.add(userStoryName);
        return title;
    }

    private JPanel buildContent(){
        JPanel content = new JPanel();
        toggle = new TaskToggle(userStory, nav);
        content.add(toggle);
        return content;
    }

    public Task getSelectedTask() { return toggle.getSelectedTask(); }

    public UserStory getSelectedUserStory() { return userStory; }


}
