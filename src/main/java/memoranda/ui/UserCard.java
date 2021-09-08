package main.java.memoranda.ui;

import main.java.memoranda.model.*;

public class UserCard extends Card {
    UserStory userStory;

    public UserCard(UserStory userStory){
        super(Integer.toString(userStory.getUserStoryNumber()), userStory.toSimpleMap());
        this.userStory = userStory;
    }

    public UserStory getUserStory() {return  userStory; }

}
