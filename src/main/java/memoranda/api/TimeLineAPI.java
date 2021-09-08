package main.java.memoranda.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class TimeLineAPI {

    public static TimeLine[] populateTimeLine(String projectID, User user, TimeLine[] timeLine) throws IOException {

        RequestConfig config = new RequestConfig();


        config.setParams("timeline/project/" + projectID);
//        System.out.println("TimeLine Endpoint --> " );

        String respString = Request.get(API_CONFIG.API_URL, user, config);
//        System.out.println("TimeLine initial response");
//        System.out.println(respString);


        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        timeLine = gson.fromJson(respString, TimeLine[].class);

        return timeLine;



    }
}
