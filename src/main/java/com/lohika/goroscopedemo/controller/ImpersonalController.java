package com.lohika.goroscopedemo.controller;


import com.lohika.goroscopedemo.dto.HoroscopeDTO;
import com.lohika.goroscopedemo.dto.UserDTO;
import com.lohika.goroscopedemo.entities.Horoscope;
import com.lohika.goroscopedemo.requesters.MultipartRequester;
import com.lohika.goroscopedemo.service.Translator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/v1/horoscope")
@Api("ImpersonalController")
public class ImpersonalController {
    private final MultipartRequester multipartRequester;

    public ImpersonalController(MultipartRequester multipartRequester) {
        this.multipartRequester = multipartRequester;
    }


    @GetMapping
            (value = "/impersonal")
    @ApiOperation("generateImpersonalHoroscope")
    public String generateImpersonalHoroscope(@RequestParam(name = "name") String name,
                                              @RequestParam(name = "day") String day,
                                              @RequestParam(name = "month") String month,
                                              @RequestParam(name = "year") String year,
                                              @RequestParam(name = "gender") String gender, ModelMap modelMap) {

        UserDTO userDTO = new UserDTO();
        userDTO.setGender(gender);
        userDTO.setUsername(name);
        userDTO.setDay(day);
        userDTO.setMonth(month);
        userDTO.setYear(year);

        HoroscopeDTO horoscope = multipartRequester.findHoroscope(userDTO);

        modelMap.addAttribute("horoscope", horoscope);

        return "horoscope";
    }
}
