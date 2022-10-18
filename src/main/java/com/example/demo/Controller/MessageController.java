package com.example.demo.Controller;

import com.example.demo.Models.Message;
import com.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/add")
    public String add(@RequestBody Message message){
        message.setMessageStatus(false);
        messageService.saveMessage(message);
        return "message added";
    }

    @GetMapping("/getAllMsgs")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }
    @GetMapping("/getMsg/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable int id){
        try {
            Message message = messageService.findMessageById(id);
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/msg/{id}")
    public String updateMessageStatus(@PathVariable int id ){
        try {
            Message message = messageService.findMessageById(id);
            message.setMessageStatus(true);
            messageService.saveMessage(message);

            return "Message Updated";
        } catch (NoSuchElementException e) {
            return "Not found";
        }
    }
}
