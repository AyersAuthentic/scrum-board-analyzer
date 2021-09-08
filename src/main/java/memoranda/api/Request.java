package main.java.memoranda.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    public static String get(String url, User user, RequestConfig config) throws IOException {
        String respString;
        String endpoint = url + config.getParams();
        //System.out.println("This is the get endpoint--> " + endpoint);


        URL urlProjects = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection)urlProjects.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization",user.getAuth_token());
        con.setRequestProperty("x-disable-pagination", "True");
        con.setDoOutput(false);

        int code = con.getResponseCode();
        //test for printout of response code
        //System.out.println("Response code->");
        //System.out.println(code);


        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            respString = response.toString();
        }
        return respString;
    }

    public static String post(String url, User user, RequestConfig config){
        String respStringPost = "";

        return respStringPost;
    }
}
