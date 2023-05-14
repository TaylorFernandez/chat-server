package com.Taylor.ChatProject.datasource.communications.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;

public class BasicResponse extends Response {
    public boolean success;
    public String description;

    public BasicResponse(boolean success, String description) {
        this.success = success;
        this.description = description;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getDescription() {
        return description;
    }

    public static String getJson(BasicResponse response) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(response);
    }
}