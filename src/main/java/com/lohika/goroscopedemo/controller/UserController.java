package com.lohika.goroscopedemo.controller;

import com.lohika.goroscopedemo.dto.UserDTO;
import com.lohika.goroscopedemo.entities.Horoscope;
import com.lohika.goroscopedemo.entities.User;
import com.lohika.goroscopedemo.service.HoroscopeServiceImpl;
import com.lohika.goroscopedemo.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping(value = "/v1/horoscope")
@Api("UserController")
public class UserController {
    private final UserServiceImpl userService;
    private final HoroscopeServiceImpl horoscopeService;

    public UserController(UserServiceImpl userService, HoroscopeServiceImpl horoscopeService) {
        this.userService = userService;
        this.horoscopeService = horoscopeService;
    }


//    @PostMapping
//    public ResponseEntity<Horoscope> createHoroscope(@RequestBody UserDTO userDTO) {
//        Horoscope result = userService.generateIndividualHoroscope(userDTO);
//        if (result==null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, check your data");
//        }
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//
//    @GetMapping(value = "/users/{id}")
//    public ResponseEntity<UserDTO> findUser(@PathVariable Long id) {
//        UserDTO result = userService.findUserById(id);
//        if (result==null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, check your data");
//        }
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//
//    @GetMapping(value = "/users")
//    public ResponseEntity<List<UserDTO>> findAllUsers() {
//        List<UserDTO> resultSet = userService.findAllUsers();
//        return new ResponseEntity<>(resultSet, HttpStatus.CREATED);
//    }

    @RequestMapping(value = "/main_user", method = RequestMethod.GET)
    @ApiOperation("mainUser")
    public String mainUser(HttpServletRequest request, ModelMap modelMap) {

        User currentUser = userFromHttpServletRequest(request);
        UserDTO userDTO = userService.findUserById(currentUser.getId());
        modelMap.addAttribute("userDTO", userDTO);
        return "main_user";
    }

    @PostMapping(value = "/main_user")
    @ApiOperation("generateImpersonalHoroscope")
    public String generateImpersonalHoroscope(@RequestParam(name = "name") String name,
                                              @RequestParam(name = "day") String day,
                                              @RequestParam(name = "month") String month,
                                              @RequestParam(name = "year") String year,
                                              @RequestParam(name = "gender") String gender, ModelMap modelMap,
                                              HttpServletRequest request) {

        User currentUser = userFromHttpServletRequest(request);

        Horoscope horoscope = userService.generateHoroscopeFromUser(currentUser, name, day, month, year, gender);

        modelMap.addAttribute("horoscope", horoscope);

        return "horoscope";
    }

    @RequestMapping(value = "/main_user", params = {"id"}, method = RequestMethod.POST)
    @ApiOperation("deleteHoroscope")
    public String deleteHoroscope(@RequestParam(name = "id") Long id, HttpServletRequest request, ModelMap modelMap) {

        horoscopeService.deleteHoroscope(id);

        return "main_user";

    }


    private User userFromHttpServletRequest(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User currentUser = (User) ((Authentication) principal).getPrincipal();
        return currentUser;
    }
}
