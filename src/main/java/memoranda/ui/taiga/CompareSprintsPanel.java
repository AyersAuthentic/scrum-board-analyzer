package main.java.memoranda.ui.taiga;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.memoranda.model.Project;
import main.java.memoranda.model.Sprint;
import main.java.memoranda.model.DataStore;
import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.ui.data.PlaceholderDataStore;

public class CompareSprintsPanel extends JPanel {
    Project project;
    
    public CompareSprintsPanel() {
        
        try {
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
        
    }
    public void jbInit() {

        this.add(selectSprints());
    }
    private JPanel selectSprints() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Please enter the sprint number you want to compare");
        JTextField sprint1 = new JTextField("enter sprint#");
        JTextField sprint2 = new JTextField("enter sprint#");
        JButton submit = new JButton("Submit"); 
        submit.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(Box.createRigidArea(new Dimension(0, 100)));
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(sprint1);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(sprint2);
        panel.add(Box.createRigidArea(new Dimension(0, 75)));
        panel.add(submit);
        
        JPanel self = this;
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(DataStore.projects == null){
                    panel.removeAll();
                    JLabel error = new JLabel("There are no projects. Please log in");
                    error.setForeground(Color.red);
                    panel.add(error);
                    panel.add(goBack());
                    panel.validate();
                    panel.repaint();
                    return;
                } else {
                     project =  DataStore.projects.get("Lebkuchen");
                }
                try {
                    int maxSprintNum = project.getSprintNames().size();
                    int s1 = Integer.parseInt(sprint1.getText());
                    int s2 = Integer.parseInt(sprint2.getText()); 
                    if((s1 <= maxSprintNum) && (s2 <= maxSprintNum) && (s1 > 0) && (s2 > 0)) {
                            panel.removeAll();
                            panel.add(Box.createRigidArea(new Dimension(0, 10)));
                            panel.add(buildContent(s1, s2)); 
                            panel.validate();
                            panel.repaint();
                    } else {
                            title.setText("enter valid sprint");
                            title.setForeground(Color.red);
                    }  
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(self, "Please enter a number only", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return panel;
    }
    /**
     * Displays two sprints next to each other
     * sprint is green if it has better rating. If sprints have the
     * same rating they will both be green
     * @param sprint1
     * @param sprint2
     * @return
     */

  private JPanel buildContent(int sprint1, int sprint2){
      JPanel content = new JPanel();
      content.add(Box.createRigidArea(new Dimension(0, 20)));
      int size = project.getSprintNames().size();
      content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
      String sprintName1 = project.getSprintNames().get(size-sprint1);
      String sprintName2 = project.getSprintNames().get(size-sprint2);
      Sprint s1 = project.getSprint(sprintName1);
      Sprint s2 = project.getSprint(sprintName2);
      
      if(s1.getSprintRating() >  s2.getSprintRating()) {
          content.add(buildSprintData(s1, true));
          content.add(Box.createRigidArea(new Dimension(0, 20)));
          content.add(buildSprintData(s2, false));
      } else if (s1.getSprintRating() ==  s2.getSprintRating()){
          content.add(buildSprintData(s1, true));
          content.add(Box.createRigidArea(new Dimension(0, 20)));
          content.add(buildSprintData(s2, true));
      } else {
          content.add(buildSprintData(s1, false));
          content.add(Box.createRigidArea(new Dimension(0, 20)));
          content.add(buildSprintData(s2, true));
      }
      content.add(goBack());
      return content; 
  }
  
  private JPanel buildSprintData(Sprint sprint, boolean best) {
      JPanel data = new JPanel();
      data.setBorder(new EmptyBorder(10, 10, 10, 10));
      data.setLayout(new BoxLayout(data, BoxLayout.Y_AXIS));
      data.add(new JLabel(sprint.getName()));
      data.add(earnedPts(sprint));
      data.add(completedTasks(sprint));
      data.add(tasksPerDay(sprint));
      data.add(sprintRating(sprint));
      if(best) {
          data.setBackground(Color.green);
          JLabel text = new JLabel("Better Sprint");
          text.setForeground(Color.white);
          data.add(text);
      } else {
          data.setBackground(Color.red);
          JLabel text2 = new JLabel("Worse Sprint");
          text2.setForeground(Color.white);
          data.add(text2);
      }
      return data;
  }
  
  

  private JLabel earnedPts(Sprint sprint) {
      JLabel earnedPoints = new JLabel("Percent points earned: " + sprint.getPercentEarnedPoints());
      return earnedPoints;
  }
  
  private JLabel completedTasks(Sprint sprint) {
      JLabel completedTasks = new JLabel("Percent of tasks complete: " + sprint.getPercentCompletionTasks());
      return completedTasks;
  }
  
  private JLabel tasksPerDay(Sprint sprint) {
      String avg = String.format("%.2f",sprint.getTaskRateOverall());
      JLabel avgTasks = new JLabel("Average tasks completed/day " + avg);
      return avgTasks;
  }
 
  //getSprintRating still has to be modified
  private JLabel sprintRating(Sprint sprint) {
      JLabel rating = new JLabel("Rating: " + sprint.getSprintRating());
      return rating;
  }
  
  private JButton goBack() {
    JButton goBack = new JButton("Go Back"); 
    goBack.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();
            add(selectSprints());
            validate();
            repaint();
        }
    });
    

    return goBack;
  }
}


