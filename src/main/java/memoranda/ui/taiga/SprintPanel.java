package main.java.memoranda.ui.taiga;

import main.java.memoranda.api.MainConnection;
import main.java.memoranda.model.*;
import main.java.memoranda.ui.TaigaPanel;
import main.java.memoranda.ui.SprintToggle;
import main.java.memoranda.ui.SprintCardPlus;
import main.java.memoranda.ui.data.*;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.memoranda.ui.ExceptionDialog;

public class SprintPanel extends JPanel{
  Project project;
  SprintToggle toggle;
  TaigaPanel nav;


  public SprintPanel(Project project) {
    this.project = project;
    try {
        jbInit();
    } catch (Exception ex) {
        new ExceptionDialog(ex);
    }
  }
 
  public SprintPanel(TaigaPanel nav){
    this.nav = nav;
    if(DataStore.projects == null){
      PlaceholderDataStore.initialize();
      project = PlaceholderDataStore.project;
      jbInit();
      return;
    }else{
      System.out.println("The projects have loaded");
    }
    JPanel selectProjectPanel = new JPanel(new FlowLayout()); 
    for(Project aProject : DataStore.projects.values()){
      JButton projectButton = new JButton(aProject.getName());
      projectButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          project = DataStore.projects.get(projectButton.getText().trim());
          remove(selectProjectPanel);
          jbInit();
          revalidate();
          repaint();
        }
      });
      selectProjectPanel.add(projectButton);
    }
    add(selectProjectPanel);
     
  }

  public void jbInit() {
    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(layout);
    this.add(createTitle());
    this.add(buildContent());
  }
  private JPanel createTitle() {
    JPanel title = new JPanel();
    JLabel projectName = new JLabel(project.getName());
    title.add(projectName);
    return title;
  }
  private JPanel buildContent(){
    JPanel content = new JPanel();
    toggle = new SprintToggle(project, nav);
    content.add(toggle);
    return content; 
  }

  public Sprint getSelectedSprint(){
    return toggle.getSelectedSprint();
  }

  public Project getSelectedProject() {return project; };
}


