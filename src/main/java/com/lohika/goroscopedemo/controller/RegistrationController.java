package com.lohika.goroscopedemo.controller;

import com.lohika.goroscopedemo.dto.CaptchaResponseDTO;
import com.lohika.goroscopedemo.dto.UserDTO;
import com.lohika.goroscopedemo.service.UserService;
import com.lohika.goroscopedemo.util.ControllerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;


@Controller
@RequestMapping(value = "/v1/horoscope")
@Api("RegistrationController")
public class RegistrationController {

    private final UserService userService;

    private final RestTemplate restTemplate;

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String secret;

    public RegistrationController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/registration")
    @ApiOperation("registrationDefaultView")
    public String registration(ModelMap modelMap, HttpSession session) {
        return "registration";
    }

    @PostMapping("/registration")
    @ApiOperation("addUser")
    public String addUser(
            @Valid UserDTO userDTO,
            BindingResult bindingResult,
            ModelMap modelMap,
            HttpSession session,
            HttpServletRequest httpServletRequest
//            @RequestParam("g-recaptcha-response") String captchaResponse
            ) {
        System.out.println("g-recaptcha-response "+ httpServletRequest.getParameter("g-recaptcha-response"));

//        String url = String.format(CAPTCHA_URL, secret, captchaResponce);
//        CaptchaResponseDTO response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);
        String result = "";
//        if (!response.isSuccess()) {
//            modelMap.addAttribute("captchaError", "Fill captcha");
//        } else {
//            result = userService.saveUser(email, password, name, day, month, year, gender);
//        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            modelMap.addAttribute("errors", errors);
            for (Map.Entry<String, String> items : errors.entrySet()) {
                System.out.println("key = " + items.getKey() + " value= " + items.getValue());
            }
            return "registration";
        }
        result = userService.saveUser(userDTO);
        modelMap.addAttribute("result", result);
        return "registration";
    }
}