package com.gorillas.springwebfluxgraphqlmongoDB;

import com.gorillas.springwebfluxgraphqlmongoDB.entity.User;
import com.gorillas.springwebfluxgraphqlmongoDB.repository.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class SpringWebfluxGraphqlMongoDbApplication {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxGraphqlMongoDbApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void appStart() {
        if (userRepository.count().block() == 0) {
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader("src/main/resources/users.json")) {

                Object obj = jsonParser.parse(reader);

                JSONArray userList = (JSONArray) obj;

                userList.forEach(user -> generate((JSONObject) user));


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void generate(JSONObject user) {
        User userObj = User.builder().firstName((String) user.get("firstName"))
                .lastName((String) user.get("lastName"))
                .userName((String) user.get("userName"))
                .password((String) user.get("password"))
                .email((String) user.get("email"))
                .build();
        userRepository.save(userObj).subscribe(System.out::println);

    }

}
