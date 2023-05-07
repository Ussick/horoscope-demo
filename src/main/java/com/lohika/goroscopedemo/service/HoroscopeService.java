package com.lohika.goroscopedemo.service;

import com.lohika.goroscopedemo.dto.UserDTO;
import com.lohika.goroscopedemo.entities.Horoscope;
import com.lohika.goroscopedemo.entities.User;

public interface HoroscopeService {

    Horoscope findHoroscopeById(Long id);

    void deleteHoroscope(Long id);
}
