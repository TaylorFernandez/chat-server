package com.Taylor.ChatProject.datasource.model.Controller;

import com.Taylor.ChatProject.datasource.communications.Request.RequestGetLoginStatus;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
import com.Taylor.ChatProject.datasource.model.Datatypes.UserLogin;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class LoginController {
    List<UserLogin> users = new ArrayList<UserLogin>(Arrays.asList(new UserLogin("taylor", "test"), new UserLogin("testUser", "test")));

    @PostMapping("/login")
    public ResponseEntity<Object> validateLogin (@RequestBody RequestGetLoginStatus request) throws JsonProcessingException {
        for(UserLogin login: users) {
            if(login.getUsername().equals(request.getUsername()) && login.getPassword().equals(request.getPassword())) {
                BasicResponse response = new BasicResponse(true, "Login successful");
                return new ResponseEntity<>(BasicResponse.getJson(response), HttpStatus.OK);
            }
        }
        BasicResponse response = new BasicResponse(false, "Failed to find matching user");
        return new ResponseEntity<>(BasicResponse.getJson(response), HttpStatus.OK);
    }
}
