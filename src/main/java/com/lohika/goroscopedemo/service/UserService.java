package com.lohika.goroscopedemo.service;

import com.lohika.goroscopedemo.dto.UserDTO;
import com.lohika.goroscopedemo.entities.Horoscope;
import com.lohika.goroscopedemo.entities.User;

import java.util.List;

public interface UserService{

    Horoscope generateIndividualHoroscope(UserDTO userDTO);

    UserDTO findUserById(Long id);

    List<UserDTO> findAllUsers();

    String saveUser(UserDTO userDTO);

    User findUserByEmail(String email);

    boolean activateUser(String code);

    Horoscope generateHoroscopeFromUser(User currentUser, String name, String day, String month, String year, String gender);
}
