//branch for user story 95

package main.java.memoranda.api;

import java.util.ArrayList;

public class ProjectData {

    private String id;
    private String name;
    private String slug;
    private String description;
    private String created_date;
    private String modified_date;
    private OwnerData owner;
    private String[] members;
    private MembershipData[] membershipData;
    private TimeLine[] timeLine;
    private SprintData[] sprintData;




    public ProjectData(){}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getModified_date() {
        return modified_date;
    }

    public OwnerData getOwner() {
        return owner;
    }

    public String[] getMembers() {
        return members;
    }

    public MembershipData[] getMembershipData() {
        return membershipData;
    }

    public TimeLine[] getTimeLine() {
        return timeLine;
    }

    public SprintData[] getSprintData() {
        return sprintData;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public void setOwner(OwnerData owner) {
        this.owner = owner;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public void setMembershipData(MembershipData[] membershipData) {
        this.membershipData = membershipData;
    }

    public void setTimeLine(TimeLine[] timeLine) {
        this.timeLine = timeLine;
    }

    public void setSprintData(SprintData[] sprintData) {
        this.sprintData = sprintData;
    }


    @Override
    public String toString(){

        return "Project [ name: "+name+", description: "+description+" , created_date: "+created_date+
                " , owner: "+owner.getFull_name_display()+ " ]";
    }
}
