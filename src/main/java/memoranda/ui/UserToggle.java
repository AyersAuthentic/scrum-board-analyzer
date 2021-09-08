package main.java.memoranda.ui;

import main.java.memoranda.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserToggle extends ToggleDisplayPanel {
    TaigaPanel nav;
    Sprint sprint;


    public UserToggle(Sprint sprint, TaigaPanel nav){
        super();
        this.nav = nav;
        this.sprint = sprint;
        carousel = new UserCarousel(sprint);
        buildContent();
        buildButton();
        buildTaskButton();
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

    private void buildTaskButton(){
        JButton userStoryButton = new JButton("View Tasks");
        userStoryButton.setPreferredSize(new Dimension(150, 50));

        userStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    nav.goTo("Task");
                } catch(Exception exception){
                    System.out.println("Nothing selected.");
                }
            }
        });
        buttonPanel.add(userStoryButton);
        revalidate();
        repaint();

    }

    public UserStory getSelectedUserStory(){
        if(mode == 0){
            return ((UserCarousel)carousel).getSelectedUserStory();
        } else {
            return ((UserCarousel)carousel).getSelectedUserStory();
        }
    }
}
