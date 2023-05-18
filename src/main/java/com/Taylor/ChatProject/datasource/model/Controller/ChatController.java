package com.Taylor.ChatProject.datasource.model.Controller;

import com.Taylor.ChatProject.datasource.communications.Request.RequestGetChats;
import com.Taylor.ChatProject.datasource.communications.Request.RequestGetPeers;
import com.Taylor.ChatProject.datasource.communications.Request.RequestSendNewChat;
import com.Taylor.ChatProject.datasource.communications.Response.BasicResponse;
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
    List<ChatThread> chats = new ArrayList<>(Arrays.asList(
            new ChatThread("taylor", "testUser", new ArrayList<>(Arrays.asList(
                    new ChatMessage("taylor", "Hello"),
                    new ChatMessage("testUser", "HI")
            )))
    ));


    private boolean isGettingMessage = false;

    @PostMapping("/chat/getChatForUsers")
    public ResponseEntity<Object> getChatsForUser(@RequestBody RequestGetChats request) throws JsonProcessingException {
        isGettingMessage = true;
        System.out.println("Fetching chats for users " + request.getUsername1() + " and " + request.getUsername2());
        for(ChatThread thread : chats) {

            if(thread.getUsername1().equals(request.getUsername1()) && thread.getUsername2().equals(request.getUsername2())
            ||thread.getUsername2().equals(request.getUsername1()) && thread.getUsername1().equals(request.getUsername2())){
                ResponseGetChats response = new ResponseGetChats(thread, true, "Found Chat thread!");

                isGettingMessage = false;
                return new ResponseEntity<>(ResponseGetChats.getJson(response), HttpStatus.OK);
            }
        }

        ResponseGetChats response = new ResponseGetChats(null, false, "Unable to find thread!");
        isGettingMessage = false;
        return new ResponseEntity<>(ResponseGetChats.getJson(response), HttpStatus.OK);
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

    @PostMapping("chat/newChat")
    public ResponseEntity<Object> newChat(@RequestBody RequestSendNewChat request) throws JsonProcessingException {
        for (ChatThread chat : chats) {
            if (chat.getUsername1().equals(request.getSender()) && chat.getUsername2().equals(request.getDestination())
            ||chat.getUsername2().equals(request.getSender()) && chat.getUsername1().equals(request.getDestination())) {
                ChatMessage temp = new ChatMessage(request.getSender(), request.getMessage());
                System.out.println("Added a new chat: " + temp.getMessage() + " from " + temp.getSender());

                while (isGettingMessage) {
                }
                chat.addChat(temp);
                System.out.println("Added chat for thread between " + request.getSender() + " and " + request.getDestination());
                BasicResponse r = new BasicResponse(true, "Successfully added a new message");
                return new ResponseEntity<>(BasicResponse.getJson(r), HttpStatus.OK);
            }
        }
        BasicResponse r = new BasicResponse(false, "Couldn't find chat thread");
        return new ResponseEntity<>(BasicResponse.getJson(r), HttpStatus.OK);
    }

}
