package com.Taylor.ChatProject.datasource.communications.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestSendNewChat implements Request{
    private String sender;
    private String destination;

    private String message;

    @JsonCreator
    public RequestSendNewChat(@JsonProperty("sender") String sender,
                              @JsonProperty("destination") String destination,
                              @JsonProperty("message") String message){
        this.sender = sender;
        this.destination = destination;
        this.message = message;
    }

    public String getSender(){
        return sender;
    }

    public String getDestination(){
        return destination;
    }

    public String getMessage(){
        return message;
    }
}
