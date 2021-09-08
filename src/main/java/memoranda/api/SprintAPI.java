package main.java.memoranda.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class SprintAPI {



public static SprintData[] populateSprints(String projectID, SprintData[] sprints, User user) throws IOException {

//    String endpoint = "https://api.taiga.io/api/v1/milestones?project=" + projectID;
//
//    //Test code
//    System.out.println("This is the sprint endpoint");
//    System.out.println(endpoint);

    RequestConfig config = new RequestConfig();
    config.setParams("milestones?project=" + projectID);

    String respString = Request.get(API_CONFIG.API_URL, user, config);


    GsonBuilder builder = new GsonBuilder();
    builder.setPrettyPrinting();

    Gson gson = builder.create();
    sprints = gson.fromJson(respString, SprintData[].class);

    for(SprintData sprint : sprints){
        UserStoryData[] userStoryData = new UserStoryData[1000];
        sprint.setUser_stories(UserStoryDataAPI.populateUserStoryData(user, projectID,userStoryData, sprint.getId()));
    }

    return sprints;

}



}
