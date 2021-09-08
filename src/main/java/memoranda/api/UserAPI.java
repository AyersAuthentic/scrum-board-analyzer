package main.java.memoranda.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class UserAPI {

    public static User loginUser(User user, String username, String password) throws IOException {


        String stringResp;

        //Desired endpoint url
        URL url = new URL ("https://api.taiga.io/api/v1/auth");

        //Opening Connection, setting request type and headers
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        //Set to true if you are sending request body usually with post and get requests
        con.setDoOutput(true);


        ///Sign in functionality needs to be implemented to get information bellow from the user
        //JSON String need to be constructed for the specific resource.
        String jsonInputString = "{\"password\": \"" + password + "\", \"type\": \"normal\"," +
                "\"username\": \"" + username + "\"}";

        //sending request to the endpoint
        try(OutputStream os = con.getOutputStream()){
            //request body setup from json input string
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        //reponse code recieved when sending the request 200=good 400=bad
        //int code = con.getResponseCode();
        //System.out.println(code);

        //reading in request response
        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }


            stringResp = response.toString();

        }


        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        user = gson.fromJson(stringResp, User.class);

        return user;

    }





}
