package main.java.memoranda.model;

import main.java.memoranda.api.*;

import java.util.HashMap;

/**
* Defines the Collaborator class, Taiga King's internal representation of a Taiga project participant
*  
* @author Lebkuchen Team
* @version 0.9
*/
public class Collaborator {
  private String username;
  private String firstName;
  private String lastName;
  private String icon;
  private String id;
  private String createdAt;
  private String project;
  private String is_user_active;

  public Collaborator(MembershipData member){
    String[] names = member.getFull_name().split(" ");
    this.firstName = names[0];
    this.lastName = names[1];
    this.icon = member.getPhoto();
    this.id = member.getUser();
    this.createdAt = member.getCreated_at();
    this.project = member.getProject();
    this.is_user_active = member.getIs_user_active();
  }



  public Collaborator(String username, String firstName, String lastName, String icon){
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.icon = icon;
  }

  /**
  * Gets the username of a collaborator
  * @return the username of a collaborator
  */
  public String getUsername(){
    return username;
  }

  /**
  * Gets the first name of a collaborator
  * @return the first name of a collaborator
  */
  public String getFirstName(){
    return firstName;
  }

  /**
  * Gets the last name of a collaborator
  * @return the last name of a collaborator
  */
  public String getLastName(){
    return lastName;
  }

  /**
  * Gets the full name of a collaborator
  * @return the full name of a collaborator
  *
  */
  public String getFullName(){
    return firstName + " " + lastName;
  }

  /**
  * Gets the icon of a collaborator
  * @return the icon of a collaborator
  */
  public String getIcon(){
    return icon;
  }

  /**
   * @return returns ID of user
   */
  public String getId() {
    return id;
  }

  /**
   * @return String with date of creation
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * @return String with project name
   */
  public String getProject() {
    return project;
  }

  /**
   * @return String indicating if user is active
   */
  public String getIs_user_active() {
    return is_user_active;
  }
}
