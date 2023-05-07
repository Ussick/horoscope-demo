package com.lohika.goroscopedemo.repository;

import com.lohika.goroscopedemo.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    @EntityGraph(value = "User.horoscopes")
    Optional<User> findUserByEmail(String email);

    Optional<User> findByActivationCode(String code);

//    @EntityGraph(value = "User.horoscopes")
//    Optional<User> findById(Long id);

}
