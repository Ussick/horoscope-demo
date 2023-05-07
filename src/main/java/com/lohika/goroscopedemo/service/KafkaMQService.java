package com.lohika.goroscopedemo.service;

import com.lohika.goroscopedemo.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;


@Service
@Slf4j
public class KafkaMQService implements Supplier<Flux<UserDTO>>, UserDTOMessageProducer {
    private final Sinks.Many<UserDTO> sink = Sinks.many().unicast().onBackpressureBuffer();

    @Override
    public void produce(UserDTO message) {
        sink.emitNext(message, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    @Override
    public Flux<UserDTO> get() {
        sink.asFlux();
        return sink.asFlux();
    }
}
