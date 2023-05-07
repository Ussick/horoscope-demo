package com.lohika.goroscopedemo.util;

import com.lohika.goroscopedemo.dto.UserDTO;
import com.lohika.goroscopedemo.entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConvertUserUtil {
    public static User convertUserFromUserDTO(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setGender(userDTO.getGender());
        String date = userDTO.getYear() + "-" + userDTO.getMonth() + "-" + userDTO.getDay();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date birthday = formatter.parse(date);
            user.setBirthDay(birthday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static UserDTO convertDTOfromUser(User user) {
        UserDTO userDTO = new UserDTO();

        Date birthDay = user.getBirthDay();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDay);
        userDTO.setDay(String.valueOf(calendar.get(Calendar.YEAR)));
        userDTO.setMonth(String.valueOf(Calendar.MONTH) + 1);
        userDTO.setDay(String.valueOf(calendar.get(Calendar.DATE)));
        userDTO.setUsername(user.getUsername());
        userDTO.setGender(user.getGender());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setHoroscopes(user.getHoroscopes());
        return userDTO;
    }
}
