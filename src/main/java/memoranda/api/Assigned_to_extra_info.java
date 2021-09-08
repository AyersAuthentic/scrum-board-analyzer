package main.java.memoranda.api;

/**
 * Defines the Assigned_to_extra_info class, Taiga King's internal data structure. Stores user information when a task
 * is assigned to a teammate.
 *
 * @author Lebkuchen Team
 * @version 0.9
 */

public class Assigned_to_extra_info {

    private String username;
    private String full_name_display;

    public Assigned_to_extra_info(){}

    /**
     * Gets the username of an assigned collaborator
     * @return the username of an assigned collaborator
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the full name of an assigned collaborator
     * @return the full name of an assigned collaborator
     */
    public String getFull_name_display() {
        return full_name_display;
    }


    /**
     * Sets the username of an assigned collaborator
     * @param username
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the full name of an assigned collaborator
     * @param full_name_display
     */
    public void setFull_name_display(String full_name_display) {
        this.full_name_display = full_name_display;
    }
}
