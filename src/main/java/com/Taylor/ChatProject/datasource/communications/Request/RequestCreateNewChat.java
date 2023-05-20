package com.Taylor.ChatProject.datasource.communications.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCreateNewChat implements Request{
    private String username;
    private String clientName;

    @JsonCreator
    public RequestCreateNewChat(@JsonProperty("username") String username,
                                @JsonProperty("clientName") String clientName){
        this.username = username;
        this.clientName = clientName;

    }

    public String getUsername(){
        return username;
    }

    public String getClientName(){return clientName;}

}
