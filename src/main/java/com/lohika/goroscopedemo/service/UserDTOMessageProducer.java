package com.lohika.goroscopedemo.service;
import com.lohika.goroscopedemo.dto.UserDTO;

public interface UserDTOMessageProducer {
    void produce(UserDTO message);

}
