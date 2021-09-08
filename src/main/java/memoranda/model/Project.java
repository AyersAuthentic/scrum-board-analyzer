package main.java.memoranda.model;

import main.java.memoranda.api.MembershipData;
import main.java.memoranda.api.ProjectData;
import main.java.memoranda.api.SprintData;
import main.java.memoranda.api.TimeLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;
import java.time.LocalDate;

/**
 * Defines the Task class, Taiga King's internal representation of a Taiga Task
 * 
 * @author Lebkuchen Team
 * @version 0.9
 */

public class Project {
	private String name;
	private String description;
	private int id;
	private List<Event> timeline;
	private HashMap<String, Collaborator> collaborators;
	private HashMap<String, Sprint> sprints;
	public static HashMap<String, Project> projects;

	public static void initialize(ProjectData[] projectData) {

		projects = new HashMap<String, Project>();

		for (ProjectData data : projectData) {
			String name = data.getName().replaceAll("(\\r|\\n)", "");
			projects.put(name, new Project(data));
		}
	}

	public Project(ProjectData projectData) {

		this.name = projectData.getName();
		this.description = projectData.getDescription();
		this.id = Integer.parseInt(projectData.getId());
		this.timeline = new ArrayList<>();
		this.collaborators = new HashMap<>();
		this.sprints = new HashMap<>();
		for (MembershipData membershipData : projectData.getMembershipData()) {
			collaborators.put(membershipData.getFull_name(), new Collaborator(membershipData));
		}
		for (SprintData sprintData : projectData.getSprintData()) {
			sprints.put(sprintData.getName(), new Sprint(sprintData, collaborators, this));
		}
		for (TimeLine event : projectData.getTimeLine()) {
			Collaborator initiator = getCollaborator(event.getData().getUser().getName());
			Event myEvent = new Event(event, initiator);
			if (myEvent.getStartState() != null) {
				timeline.add(new Event(event, initiator));
			}
		}
	}

	public Project(String name, String description, int id, List<Event> timeline,
			HashMap<String, Collaborator> collaborators, HashMap<String, Sprint> sprints) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.timeline = timeline;
		this.collaborators = collaborators;
		this.sprints = sprints;
	}

	/**
	 * Gets the name of the project
	 * 
	 * @return the the name of the project
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the description of the project
	 * 
	 * @return the description of the project
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the id of the project
	 * 
	 * @return the id of the project
	 */
	public int getID() {
		return id;
	}

	/**
	 * Gets all events associated with the project
	 * 
	 * @return a list of the events associated with the project
	 */
	public List<Event> getTimeline() {
		return timeline;
	}

	/**
	 * Gets all events associated with the project on a particular date
	 * 
	 * @param date is the date of interest for the information
	 * @return all events associated with the project on a particular date
	 */
	public List<Event> getDaysTimeline(String date) {
		List<Event> dayTimeline = new LinkedList<>();
		for (Event event : timeline) {
			if (event.getDate().equals(LocalDate.parse(date))) {
				dayTimeline.add(event);
			}
		}
		return dayTimeline;
	}

	public static Project getProject(String projectName) {
		return projects.get(projectName);
	}

	public static List<String> getProjectNames() {
		return new ArrayList<String>(projects.keySet());
	}

	/**
	 * Gets the usernames of all who particpated in the project
	 * 
	 * @return a list of usernames
	 */
	public List<String> getCollaboratorNames() {
		return new LinkedList<String>(collaborators.keySet());
	}


	/**
	 * Gets a particular collaborator based on username
	 * 
	 * @param username is the name of a particular collaborator
	 * @return the collaborator with a particular username
	 */
	public Collaborator getCollaborator(String username) {
		return collaborators.get(username);
	}

	public HashMap<String, Collaborator> getCollaborators() {
		return collaborators;
	}

	/**
	 * Gets the names of all sprints associated with the project
	 * 
	 * @return the names of all sprints associated with the project
	 */
	public List<String> getSprintNames() {
		return new LinkedList<String>(sprints.keySet());
	}

	/**
	 * Gets a particular sprint associated with the project based on name
	 * 
	 * @param sprintName is the name associated with a particular sprint
	 * @return a particular sprint associated with the project based on name
	 */
	public Sprint getSprint(String sprintName) {
		return sprints.get(sprintName);
	}

	public Sprint getArbitrarySprint() {
		for (Sprint sprint : sprints.values()) {
			return sprint;
		}
		return null;
	}

	public HashMap<String, Sprint> getSprints() {
		return sprints;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTimeline(List<Event> timeline) {
		this.timeline = timeline;
	}

	public void setCollaborators(HashMap<String, Collaborator> collaborators) {
		this.collaborators = collaborators;
	}

	public void setSprints(HashMap<String, Sprint> sprints) {
		this.sprints = sprints;
	}

	public LinkedHashMap<String, String> toSimpleMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("Title", getName());
		map.put("Description", getDescription().toString());
		map.put("Project ID", "" + getID());
		return map;

	}

	public LinkedHashMap<String, String> toAdvancedMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		ProjectData data = new ProjectData();
		map.put("Title", getName());
		map.put("Description", getDescription().toString());
		map.put("Project ID", "" + getID());
		map.put("Sprints", "" + getSprintNames());
		map.put("Collaborators",
				"Kevin Ayers, Trent Engleman, Michael Schnapp, Maija Kingston, Jemimah Thomas, Jessica Kuksuk");
		return map;
	}
}
