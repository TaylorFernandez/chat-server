package com.Taylor.ChatProject.datasource.communications.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestGetPeers implements Request{
    private String username;

    @JsonCreator
    public RequestGetPeers(@JsonProperty("username") String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
}
