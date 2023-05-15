package com.Taylor.ChatProject.datasource.communications.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestGetChats implements Request{
    private final String username1;
    private final String username2;

    @JsonCreator
    public RequestGetChats(@JsonProperty("username1") String username1,
                           @JsonProperty("username2") String username2){
        this.username1 = username1;
        this.username2 = username2;
    }

    public String getUsername1(){
        return username1;
    }

    public String getUsername2(){
        return username2;
    }
}
