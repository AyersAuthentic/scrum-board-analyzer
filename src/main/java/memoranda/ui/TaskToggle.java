package main.java.memoranda.ui;

import main.java.memoranda.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskToggle extends ToggleDisplayPanel {
    TaigaPanel nav;
    UserStory userStory;

    public TaskToggle(UserStory userStory, TaigaPanel nav){
        super();
        this.nav = nav;
        this.userStory = userStory;
        carousel = new TaskCarousel(userStory);
        buildContent();
        buildButton();

    }

    private void buildButton(){
        JButton button = new JButton("View Details");
        button.setPreferredSize(new Dimension(150, 50));

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {

                    //add something here;
                } catch(Exception exception){
                    System.out.println("Nothing selected.");
                }
            }
        });
        buttonPanel.add(button);
        revalidate();
        repaint();
    }

    public Task getSelectedTask(){

        return null;
    }


}
