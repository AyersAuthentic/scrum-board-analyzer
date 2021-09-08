package main.java.memoranda.ui.taiga;

import main.java.memoranda.model.*;
import main.java.memoranda.ui.Card;
import main.java.memoranda.ui.Carousel;
import main.java.memoranda.ui.data.*;
import java.util.LinkedList;

import javax.swing.BoxLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.ui.TaigaPanel;

public class DashPanel extends JPanel{
    private TaigaPanel nav;
    public DashPanel(TaigaPanel nav) {
      this.nav = nav;
      try {
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    public void jbInit() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.add(createTitle());
        this.add(buildContent());
    }
  private JPanel createTitle() {
      JPanel title = new JPanel();
      PlaceholderDataStore.initialize();
      Project project = PlaceholderDataStore.project;
      JLabel projectName = new JLabel(project.getName());
      title.add(projectName);
      return title;
  }
  private JPanel buildContent(){
    JPanel content = new JPanel();
    PlaceholderDataStore.initialize();
    Project project = PlaceholderDataStore.project;   
    LinkedList<Card> cards = new LinkedList<Card>();
    for(String sprintName : project.getSprintNames()){
      Sprint sprint = project.getSprint(sprintName);
      Card card = new Card(sprint.getName(), sprint.toSimpleMap());
      card.addItem("Points Earned", "19");
      card.addItem("Points Possible", "20");
      card.addItem("Percent Tasks Complete", "89%");
      card.addItem("Average Task per Day", "2");  
      cards.add(card);
    } 
    Carousel carousel = new Carousel(cards);
    content.add(carousel);
    return content;
    
    
  }
}


