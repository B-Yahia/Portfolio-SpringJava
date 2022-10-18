package com.example.demo.Service;

import com.example.demo.Models.Message;

import java.util.List;

public interface MessageService {
    public Message saveMessage(Message message);
    public List<Message> getAllMessages();
    public Message findMessageById(int id);
}
