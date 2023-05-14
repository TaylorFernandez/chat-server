package com.Taylor.ChatProject.datasource.Controller;

import com.Taylor.ChatProject.datasource.communications.Request.RequestGetLoginStatus;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<Object> validateLogin (@RequestBody RequestGetLoginStatus request) throws JsonProcessingException {
        BasicResponse response = new BasicResponse(true, "Response from Server!");
        return new ResponseEntity<>(BasicResponse.getJson(response), HttpStatus.OK);
    }
}
