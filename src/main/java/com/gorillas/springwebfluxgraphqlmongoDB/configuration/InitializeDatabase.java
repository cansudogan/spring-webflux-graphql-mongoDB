package com.gorillas.springwebfluxgraphqlmongoDB.configuration;

import com.gorillas.springwebfluxgraphqlmongoDB.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Component
public class InitializeDatabase {
    private final UserService userService;

    public InitializeDatabase(UserService userService) {
        this.userService = userService;
    }

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (userService.userCount().block() == 0) {
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader("src/main/resources/users.json")) {

                Object obj = jsonParser.parse(reader);

                JSONArray userList = (JSONArray) obj;

                userList.forEach(user -> userService.createUser((JSONObject) user));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
