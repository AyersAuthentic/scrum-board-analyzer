package main.java.memoranda.ui;

import main.java.memoranda.model.DataStore;
import main.java.memoranda.model.Task;
import main.java.memoranda.model.UserStory;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TaskCarousel extends Carousel {
    protected UserStory userStory;
    protected TaskCard selected;
    protected int size = 0;

    public TaskCarousel(UserStory story){
        super();
        this.userStory = story;
        CardListener listener = new CardListener();
        for(Integer taskNum : userStory.getTaskNumbers()){
            Task task = userStory.getTask(taskNum);
            TaskCard card = new TaskCard(task);
            card.addMouseListener(listener);
            addCard(card);
            size++;
        }
        scrollPane.setPreferredSize(new Dimension((int) ((int) cardsPane.getPreferredSize().getWidth() / size * 2.5), (int) cardsPane.getPreferredSize().getHeight()));

    }

    private class CardListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent event){
            if (selected != null){
                selected.deselect();
            }
            Object source = event.getSource();
            if (source instanceof TaskCard){
                selected = (TaskCard) source;
            }
            selected.select();
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    public Task getSelectedTask() { return selected.getTask(); }


}
