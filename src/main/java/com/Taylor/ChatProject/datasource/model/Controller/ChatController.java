package com.Taylor.ChatProject.datasource.model.Controller;

import com.Taylor.ChatProject.datasource.communications.Request.RequestGetChats;
import com.Taylor.ChatProject.datasource.communications.Request.RequestGetPeers;
import com.Taylor.ChatProject.datasource.communications.Response.ResponseGetChats;
import com.Taylor.ChatProject.datasource.communications.Response.ResponseGetPeers;
import com.Taylor.ChatProject.datasource.model.Datatypes.ChatMessage;
import com.Taylor.ChatProject.datasource.model.Datatypes.ChatThread;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ChatController {
    List<ChatThread> chats = new ArrayList<ChatThread>(Arrays.asList(new ChatThread("taylor", "testUser",
            Arrays.asList(new ChatMessage("taylor", "Hello"),
                    new ChatMessage("testUser", "HI")))));

    @PostMapping("/chat/getChatForUsers")
    public ResponseEntity<Object> getChatsForUser(@RequestBody RequestGetChats request) throws JsonProcessingException {
        for(ChatThread thread : chats) {
            if(thread.getUsername1().equals(request.getUsername1()) && thread.getUsername2().equals(request.getUsername2())
            ||thread.getUsername2().equals(request.getUsername1()) && thread.getUsername1().equals(request.getUsername2())){
                ResponseGetChats response = new ResponseGetChats(thread, true, "Found Chat thread!");
                return new ResponseEntity<>(ResponseGetChats.getJson(response), HttpStatus.OK);
            }
            System.out.println("Sending notification");

            ResponseGetChats response = new ResponseGetChats(null, false, "Unable to find thread!");
            System.out.println("GetChats: " + ResponseGetChats.getJson(response));
            return new ResponseEntity<>(ResponseGetChats.getJson(response), HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/chat/getPeers")
    public ResponseEntity<Object> getPeers(@RequestBody RequestGetPeers request) throws JsonProcessingException {
        List<String> peers = new ArrayList<String>();
        for(ChatThread thread : chats){
            if(thread.getUsername1().equals(request.getUsername())){
                peers.add(thread.getUsername2());
                System.out.println("added");
            }else if(thread.getUsername2().equals(request.getUsername())){
                peers.add(thread.getUsername1());
                System.out.println("added");
            }
            System.out.println(thread.toString());
        }
        ResponseGetPeers r = new ResponseGetPeers(true, "Successfully got peers", peers);
        return new ResponseEntity<>(ResponseGetPeers.getJson(r), HttpStatus.OK);
    }

}
