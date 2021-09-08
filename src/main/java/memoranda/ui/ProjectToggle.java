package main.java.memoranda.ui;

import main.java.memoranda.model.*;

public class ProjectToggle extends ToggleDisplayPanel {
	public ProjectToggle(Project project) {
		super();
		carousel = new ProjectCarousel(project);
		table = new ProjectTable(project);
		buildContent();
	}

	public Sprint getSelectedSprint() {
		if (mode == 0) {
			return ((SprintCarousel) carousel).getSelectedSprint();
		} else {
			return ((SprintTable) table).getSelectedSprint();
		}
	}
}