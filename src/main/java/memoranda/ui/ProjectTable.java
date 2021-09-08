package main.java.memoranda.ui;

import java.util.LinkedList;
import java.util.HashMap;
import main.java.memoranda.model.*;

public class ProjectTable extends Table {
	Project project;

	public ProjectTable(Project project) {
		super(new LinkedList<String>(project.toSimpleMap().keySet())); // get keyset of arbitrary Sprint map for columns
		this.project = project;
		boolean first = true;
		for (String sprintName : project.getSprintNames()) {
			// get sprint from project
			Sprint sprint = project.getSprint(sprintName);
			// get map from sprint
			HashMap<String, String> map = project.toSimpleMap();
			Object[] values = new Object[map.size()]; // create an Object[] capable of holding all values associated
														// with the sprint
			map.values().toArray(values); // write sprint's map's values to Object[]
			addRow(values); // pass Object[] as new row to table
		}
	}

	public Sprint getSelectedSprint() {
		int column = 0; // this is sprint name column
		int row = table.getSelectedRow();
		return project.getSprint(table.getModel().getValueAt(row, column).toString());
	}
}