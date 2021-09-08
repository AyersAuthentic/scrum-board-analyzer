package main.java.memoranda.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class TaskAPI {


    public static TaskData[] populateTasks(String projectID, TaskData[] tasks, User user, String sprintID, String userStoryID) throws IOException {



        RequestConfig config = new RequestConfig();


        config.setParams("tasks?project=" + projectID + "&" + "user_story=" + userStoryID);

        String respString = Request.get(API_CONFIG.API_URL, user, config);


        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        tasks = gson.fromJson(respString, TaskData[].class);

        return tasks;

    }
}
