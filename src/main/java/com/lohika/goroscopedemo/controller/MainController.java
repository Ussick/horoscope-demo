package com.lohika.goroscopedemo.controller;

import com.lohika.goroscopedemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/v1/horoscope")
@Api("MainController")
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/", "/main"})
    @ApiOperation("greeting")
    public String greeting() {
        return "main";
    }


    @GetMapping("/activate/{code}")
    @ApiOperation("activate")
    public String activate(ModelMap modelmap, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            modelmap.addAttribute("messageActivation", "User successfully activated");
        } else {
            modelmap.addAttribute("messageActivation", "Activation code is not found!");
        }

        return "login";
    }
}
