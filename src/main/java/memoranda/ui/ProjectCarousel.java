package main.java.memoranda.ui;

import java.util.LinkedList;

import main.java.memoranda.model.*;
import java.awt.event.*;
import java.awt.Dimension;

public class ProjectCarousel extends Carousel {
	protected Project project;
	protected ProjectCard selected;
	protected int size = 0;

	public ProjectCarousel(Project project) {
		super();
		CardListener listener = new CardListener();
		for (String sprintName : project.getSprintNames()) {
			ProjectCard card = new ProjectCard(project);
			card.addMouseListener(listener);
			addCard(card);
			size++;
		}
		scrollPane.setPreferredSize(new Dimension((int) ((int) cardsPane.getPreferredSize().getWidth() / size * 2.5),
				(int) cardsPane.getPreferredSize().getHeight()));
	}

	private class CardListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent event) {
			if (selected != null) {
				selected.deselect();
			}
			Object source = event.getSource();
			if (source instanceof ProjectCard) {
				selected = (ProjectCard) source;
			}
			selected.select();
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}

	public Sprint getSelectedSprint() {
		return selected.getSprint();
	}
}