package com.lohika.goroscopedemo.service;


import com.lohika.goroscopedemo.dto.HoroscopeDTO;
import com.lohika.goroscopedemo.dto.UserDTO;
import com.lohika.goroscopedemo.entities.Horoscope;
import com.lohika.goroscopedemo.entities.Role;
import com.lohika.goroscopedemo.entities.User;
import com.lohika.goroscopedemo.repository.HoroscopeRepository;
import com.lohika.goroscopedemo.repository.UserRepository;
import com.lohika.goroscopedemo.requesters.MultipartRequester;
import com.lohika.goroscopedemo.util.ConvertHoropUtil;
import com.lohika.goroscopedemo.util.ConvertUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("user-producer")
    private UserDTOMessageProducer kafkaMQService;

    private final UserRepository userRepository;

    private final Translator translator;
    private final HoroscopeRepository horoscopeRepository;

    private final MultipartRequester multipartRequester;

    private final MailSender mailSender;

    public UserServiceImpl(UserRepository userRepository, Translator translator, HoroscopeRepository horoscopeRepository, MultipartRequester multipartRequester, MailSender mailSender) {
        this.userRepository = userRepository;
        this.translator = translator;
        this.horoscopeRepository = horoscopeRepository;
        this.multipartRequester = multipartRequester;
        this.mailSender = mailSender;
    }

    @Override
    public Horoscope generateIndividualHoroscope(UserDTO userDTO) {
        HoroscopeDTO horoscopeDTO = multipartRequester.findHoroscope(userDTO);
        horoscopeDTO = translator.translateHoroscope(horoscopeDTO);

        Horoscope horoscope = ConvertHoropUtil.convertHoroscopeFromDTO(horoscopeDTO);

        User userFromRepo = userRepository.findUserByEmail(userDTO.getEmail()).orElse(null);

        if (userFromRepo != null) {
            horoscope.setUser(userFromRepo);
        }

        horoscope = horoscopeRepository.save(horoscope);

        return horoscope;
    }

    @Override
    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        UserDTO userDTO = ConvertUserUtil.convertDTOfromUser(user);
        return userDTO;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> usersFromBase = userRepository.findAll();
        if (usersFromBase == null) {
            return new ArrayList<UserDTO>();
        }
        return usersFromBase.stream().map(user -> ConvertUserUtil.convertDTOfromUser(user)).collect(Collectors.toList());
    }

    @Override
    public String saveUser(UserDTO userDTO) {
        if (userRepository.findUserByEmail(userDTO.getEmail()).orElse(null) != null) {
            return "даний email зайнято";
        }

        User user = ConvertUserUtil.convertUserFromUserDTO(userDTO);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        User userFromDb = userRepository.save(user);
        UserDTO toSend = ConvertUserUtil.convertDTOfromUser(user);
//        kafkaMQService.produce(toSend);


        if (user.getEmail() != null) {
            String message = String.format("Hello, %s! \n" +
                            "Welcome to Horoscope. Please, visit next link: http:://localhost:8080/v1/horoscope/activate/%s",
                    user.getUsername(), user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation code", message);

        }

        generateIndividualHoroscope(userDTO);


        return "Реєстрація успішна. Лишився один крок - підтвердіть свій аккаунт, відвідавши свою поштову скриньку";
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code).orElse(null);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }

    @Override
    public Horoscope generateHoroscopeFromUser(User currentUser, String name, String day, String month, String year, String gender) {
        UserDTO userDTO = new UserDTO();
        userDTO.setGender(gender);
        userDTO.setUsername(name);
        userDTO.setDay(day);
        userDTO.setMonth(month);
        userDTO.setYear(year);
        HoroscopeDTO horoscopeDTO = multipartRequester.findHoroscope(userDTO);
        horoscopeDTO = translator.translateHoroscope(horoscopeDTO);
        Horoscope horoscope = ConvertHoropUtil.convertHoroscopeFromDTO(horoscopeDTO);
        horoscope.setUser(currentUser);
        horoscope = horoscopeRepository.save(horoscope);
        return horoscope;
    }
}
