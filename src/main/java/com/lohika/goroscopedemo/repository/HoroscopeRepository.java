package com.lohika.goroscopedemo.repository;

import com.lohika.goroscopedemo.entities.Horoscope;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.List;
import java.util.Optional;

public interface HoroscopeRepository extends JpaRepository<Horoscope, Long> {
    //    @EntityGraph(attributePaths = {"user"})
//    @Query("SELECT * FROM Horoscope h LEFT JOIN FETCH h.user WHERE horoscope.id=: id")
//    Optional<Horoscope>findById(Long id);
//    @EntityGraph(value = "Horoscope.user")
//    List<Horoscope> findAll();
//
//    @EntityGraph(value = "Horoscope.user")
//    Optional<Horoscope> findById(Long id);
}
