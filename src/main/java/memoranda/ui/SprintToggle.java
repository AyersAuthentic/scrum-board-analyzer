package main.java.memoranda.ui;

import main.java.memoranda.model.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class SprintToggle extends ToggleDisplayPanel {
  TaigaPanel nav;
  Project project;

  public SprintToggle(Project project, TaigaPanel nav){
    super();
    this.nav = nav;
    this.project = project;
    carousel = new SprintCarousel(project);
    table = new SprintTable(project);
    buildContent();
    buildButton();
    buildUserStoryButton();
  }
  
  private void buildButton(){
    JButton button = new JButton("View Details");
    button.setPreferredSize(new Dimension(150, 50));

    button.addActionListener(new ActionListener(){
     @Override
     public void actionPerformed(ActionEvent e){
       try {
         JOptionPane.showMessageDialog(
           null, 
           new SprintCardPlus(getSelectedSprint()),
           getSelectedSprint().getName(),
           JOptionPane.PLAIN_MESSAGE);
      
       } catch(Exception exception){
         System.out.println("Nothing selected.");
       }
     }
   });
   buttonPanel.add(button);
   revalidate();
   repaint();
  }

  private void buildUserStoryButton(){
      JButton userStoryButton = new JButton("View User Stories");
      userStoryButton.setPreferredSize(new Dimension(150, 50));

      userStoryButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
              try {
                  nav.goTo("UserStory");
              } catch(Exception exception){
                  System.out.println("Nothing selected.");
              }
          }
      });
      buttonPanel.add(userStoryButton);
      revalidate();
      repaint();


  }
  
  public Sprint getSelectedSprint(){
    if(mode == 0){
      return ((SprintCarousel)carousel).getSelectedSprint();
    } else {
      return ((SprintTable)table).getSelectedSprint();
    }
  } 
}
