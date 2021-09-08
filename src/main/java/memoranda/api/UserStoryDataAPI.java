package main.java.memoranda.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.memoranda.model.UserStory;

import java.io.IOException;

public class UserStoryDataAPI {

    public UserStoryDataAPI(){}

    public static UserStoryData[] populateUserStoryData(User user, String projectID, UserStoryData[] userStories, String sprintID) throws IOException {

        RequestConfig config = new RequestConfig();
        config.setParams("userstories?project=" + projectID + "&milestone=" + sprintID);

        String respString = Request.get(API_CONFIG.API_URL, user, config);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        userStories = gson.fromJson(respString, UserStoryData[].class);

        for(UserStoryData userStory : userStories){
            TaskData[] tasks = new TaskData[1000];
            userStory.setTaskData(TaskAPI.populateTasks(projectID, tasks, user, sprintID, userStory.getId()));
        }

        return userStories;

    }
}
