package com.lohika.goroscopedemo.service;


import com.lohika.goroscopedemo.entities.Horoscope;
import com.lohika.goroscopedemo.repository.HoroscopeRepository;
import com.lohika.goroscopedemo.repository.UserRepository;
import com.lohika.goroscopedemo.requesters.MultipartRequester;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class HoroscopeServiceImpl implements HoroscopeService {

    private final UserRepository userRepository;

    private final Translator translator;
    private final HoroscopeRepository horoscopeRepository;

    private final MultipartRequester multipartRequest;

    @PostConstruct
    public void init() {
        System.out.println("Hello from postConstruct");
    }

    public HoroscopeServiceImpl(UserRepository userRepository, Translator translator, HoroscopeRepository horoscopeRepository, MultipartRequester multipartRequest) {
        this.userRepository = userRepository;
        this.translator = translator;
        this.horoscopeRepository = horoscopeRepository;
        this.multipartRequest = multipartRequest;
    }

    @Override
    public Horoscope findHoroscopeById(Long id) {
        Horoscope horoscope = horoscopeRepository.findById(id).orElse(null);
        return horoscope;
    }

    @Override
    public void deleteHoroscope(Long id) {
        horoscopeRepository.deleteById(id);
    }
}
