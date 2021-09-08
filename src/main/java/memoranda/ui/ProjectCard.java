  package main.java.memoranda.ui;

import main.java.memoranda.model.*;
import java.util.LinkedList;

public class ProjectCard extends Card {
	Project project;

	public ProjectCard(Project project) {
		super(project.getName(), project.toAdvancedMap());
		this.project = project;
	}

	public Sprint getSprint() {
		return null;
	}
}