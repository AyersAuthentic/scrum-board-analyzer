package main.java.memoranda.ui;

import main.java.memoranda.model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserCarousel extends Carousel {
    protected Sprint sprint;
    protected UserCard selected;
    protected int size = 0;

    public UserCarousel(Sprint sprint) {
        super();
        CardListener listener = new CardListener();
        for (Integer userStoryNum : sprint.getUserStoryNumbers()) {
            UserStory userStory = sprint.getUserStory(userStoryNum);
            UserCard card = new UserCard(userStory);
            card.addMouseListener(listener);
            addCard(card);
            size++;
        }
        scrollPane.setPreferredSize(new Dimension((int) ((int) cardsPane.getPreferredSize().getWidth() / size * 2.5), (int) cardsPane.getPreferredSize().getHeight()));

    }

    private class CardListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent event) {
            if (selected != null) {
                selected.deselect();
            }
            Object source = event.getSource();
            if (source instanceof UserCard) {
                selected = (UserCard) source;
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

    public UserStory getSelectedUserStory() { return selected.getUserStory(); }


}
