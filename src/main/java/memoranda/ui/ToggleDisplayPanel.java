package main.java.memoranda.ui;

import main.java.memoranda.ui.Carousel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;

import main.java.memoranda.ui.ExceptionDialog;

public class ToggleDisplayPanel extends JPanel {
    protected Table table;
    protected Carousel carousel;
    JButton toggle;
    protected JPanel buttonPanel;
    protected int mode = 0;
  
    public ToggleDisplayPanel(Table table, Carousel carousel) {
        this.table = table;
        this.carousel = carousel;

        try {
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
        buildContent();
    }
   
    public ToggleDisplayPanel(){  
      jbInit(); 
    }
  
    public void jbInit() {
      setLayout(new BorderLayout());
    }

    public void createButtonPanel(){
      toggle = new JButton("Toggle View");
      toggle.setPreferredSize(new Dimension(150, 50));
      toggle.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
//          System.out.println("Action performed " + mode);
          if(mode == 0){
            remove(carousel);
            add(table, BorderLayout.CENTER);
            mode = 1; 
          } else {
            remove(table);
            add(carousel, BorderLayout.CENTER);
            mode = 0;
          }
          revalidate();
          repaint();  
        }
      });
      buttonPanel= new JPanel();
      buttonPanel.setLayout(new FlowLayout());
      buttonPanel.add(toggle); 
    }
  
    protected void buildContent(){
      createButtonPanel();
      add(buttonPanel, BorderLayout.SOUTH);
      add(carousel, BorderLayout.CENTER);
    }
}


