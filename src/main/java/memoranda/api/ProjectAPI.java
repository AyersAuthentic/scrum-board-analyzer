package main.java.memoranda.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProjectAPI {

    public static ProjectData[]  populateProjects(User user, ProjectData[] projects) throws IOException {

        RequestConfig config = new RequestConfig();
        config.setParams("projects?member=" + user.getId());

        String respString = Request.get(API_CONFIG.API_URL, user, config);


        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        projects = gson.fromJson(respString, ProjectData[].class);

        for (ProjectData project : projects){
            MembershipData[] members = new MembershipData[100];
            TimeLine[] timeLine = new TimeLine[1000];
            SprintData[] sprints = new SprintData[50];
            project.setMembershipData(MembershipData.populateMemberships(user, project.getId(), members));
            project.setTimeLine(TimeLineAPI.populateTimeLine(project.getId(), user, timeLine));
            project.setSprintData(SprintAPI.populateSprints(project.getId(), sprints, user));
        }


        return projects;


    }
}
