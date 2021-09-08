package main.java.memoranda.ui.taiga;

import main.java.memoranda.model.*;
import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.ui.TaigaPanel;
import main.java.memoranda.ui.UserToggle;
import main.java.memoranda.ui.data.PlaceholderDataStore;

import javax.swing.*;

public class UserStoryPanel extends JPanel {
    TaigaPanel nav;
    UserToggle toggle;
    Sprint sprint;

    public UserStoryPanel(TaigaPanel nav, Sprint sprint) {
        this.nav = nav;
        if(DataStore.projects == null){
            PlaceholderDataStore.initialize();
            this.sprint = PlaceholderDataStore.project.getSprint("This is a sprint");
            jbInit();
            return;
        }
        this.sprint= sprint;
        jbInit();
        revalidate();
        repaint();

    }

    public void jbInit() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.add(createTitle());
        this.add(buildContent());
    }

    private JPanel createTitle() {
        JPanel title = new JPanel();
        JLabel sprintName = new JLabel(sprint.getName());
        title.add(sprintName);
        return title;
    }

    private JPanel buildContent() {
        JPanel content = new JPanel();
        toggle = new UserToggle(sprint, nav);
        content.add(toggle);
        return content;
    }

    public UserStory getSelectedUserStory() { return toggle.getSelectedUserStory(); }

    public Sprint getSelectedSprint() { return sprint; }





}
