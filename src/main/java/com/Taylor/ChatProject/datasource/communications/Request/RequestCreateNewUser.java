package com.Taylor.ChatProject.datasource.communications.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCreateNewUser implements Request{
    private String username;
    private String password;

    @JsonCreator
    public RequestCreateNewUser(@JsonProperty("username") String username,
                                @JsonProperty("password") String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
