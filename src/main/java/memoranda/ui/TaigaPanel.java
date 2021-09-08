package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.CardLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.memoranda.CurrentProject;
import main.java.memoranda.Resource;
import main.java.memoranda.model.Project;
import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.ui.taiga.*;
import main.java.memoranda.util.AppList;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.MimeType;
import main.java.memoranda.util.MimeTypesList;
import main.java.memoranda.util.Util;

import java.io.*;

public class TaigaPanel extends JPanel {
    private JPanel contentPane;
    private AuthPanel auth = new AuthPanel(this);
    private DashPanel dash = new DashPanel(this);
    private SprintPanel sprint = new SprintPanel(this);
    private UserStoryPanel userStory = new UserStoryPanel(this, null);
    private TaigaTaskPanel task = new TaigaTaskPanel(this, null);
    public TaigaPanel() {
        this.run();
    }
    private void run()
    {
        this.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setLayout(new CardLayout());
        this.add(this.auth, "Auth");
        this.add(this.dash, "Dash");
        this.add(this.sprint, "Sprint");
        this.add(this.userStory, "UserStory");
        this.add(this.task, "Task");
    }

    public void goTo(String loc) {
        if(loc.equals("Sprint")){
            sprint = new SprintPanel(this);
            this.add(this.sprint, "Sprint");
        }else if(loc.equals("UserStory")){
            userStory = new UserStoryPanel(this, sprint.getSelectedSprint());
            this.add(this.userStory, "UserStory");
        }else if(loc.equals("Task")){
            task = new TaigaTaskPanel(this, userStory.getSelectedUserStory());
            this.add(this.task, "Task");
        }
        CardLayout c = (CardLayout)this.getLayout();
        c.show(this, loc);
    }

}
