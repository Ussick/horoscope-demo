package com.lohika.goroscopedemo.controller;

import com.lohika.goroscopedemo.entities.Horoscope;
import com.lohika.goroscopedemo.service.HoroscopeServiceImpl;
import com.lohika.goroscopedemo.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * has not used in this mode of the app
 */
@RestController
@RequestMapping(value = "/v1/horoscope")
@Api("HoroscopeController")
public class HoroscopeController {
    private final UserServiceImpl userService;
    private final HoroscopeServiceImpl horoscopeService;

    public HoroscopeController(UserServiceImpl userService, HoroscopeServiceImpl horoscopeService) {
        this.userService = userService;
        this.horoscopeService = horoscopeService;
    }



    @GetMapping(value = "/my_horoscopes/{id}")
    @ApiOperation("findHoroscope")
    public ResponseEntity<Horoscope> findHoroscope(@PathVariable Long id) {
        Horoscope result = horoscopeService.findHoroscopeById(id);
        if (result==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, check your data");
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
