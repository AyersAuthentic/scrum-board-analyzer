import java.util.HashMap;
import java.util.LinkedList;

import main.java.memoranda.model.*;
import main.java.memoranda.ui.data.*;
import main.java.memoranda.ui.*;
import main.java.memoranda.ui.taiga.*;
import main.java.memoranda.api.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public final class SimpleSwing {
  public static void main(String[] aArgs){
    SimpleSwing app = new SimpleSwing();
    app.buildAndDisplayGui();
  }

  private void buildAndDisplayGui(){
    JFrame frame = new JFrame("Test Frame");
    frame.setLayout(new FlowLayout()); 
    buildContent(frame);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
  
  private void buildContent(JFrame aFrame){
//    try {
//      MainConnection.connect("kaayers1", "myPassword");
//    } catch (Exception e){
//      System.out.println();
//    }
//    HashMap<String, Project> projects = DataStore.projects;
//    Project project = projects.get("Lebkuchen");

    PlaceholderDataStore.initialize();
    Project project = PlaceholderDataStore.project;
    SprintPanel sprintPanel = new SprintPanel(project);
    aFrame.getContentPane().add(sprintPanel);
//    PlaceholderDataStore.initialize();
//    Project project = PlaceholderDataStore.project;
//    for(String sprintName : project.getSprintNames()){
//        Sprint sprint = project.getSprint(sprintName);
//        CardPlus card = new CardPlus(sprint.getName(), sprint.toAdvancedMap());
//        aFrame.getContentPane().add(card); 
//    }
    //testing table selections
   // JButton button = new JButton("Select");
   // button.addActionListener(new ActionListener(){
   //   @Override
   //   public void actionPerformed(ActionEvent e){
   //     try {
   //       System.out.println(toggle.getSelectedSprint().getName());
   //     } catch(Exception exception){
   //       System.out.println("Nothing selected.");
   //     }
   //   }
   // });
   // aFrame.getContentPane().add(button, BorderLayout.SOUTH);
  }
}
