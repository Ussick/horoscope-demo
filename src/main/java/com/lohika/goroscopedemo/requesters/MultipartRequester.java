package com.lohika.goroscopedemo.requesters;


import com.lohika.goroscopedemo.config.HorConfig;
import com.lohika.goroscopedemo.dto.HoroscopeDTO;
import com.lohika.goroscopedemo.dto.UserDTO;
import com.lohika.goroscopedemo.service.Translator;
import com.lohika.goroscopedemo.util.ParserHoroscope;


import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;


import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultipartRequester {

    @Autowired
    public HorConfig horConfig;

    private final Translator translator;

    public MultipartRequester(Translator translator) {
        this.translator = translator;
    }

    public HoroscopeDTO findHoroscope(UserDTO userDTO){
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                // FORM
                .addPart("appbundle_personal_horoscope[name]",
                        new StringBody(userDTO.getUsername(),
                                ContentType.create("application/x-www-form-urlencoded", StandardCharsets.UTF_8)))
                .addPart("appbundle_personal_horoscope[dateBirth][day]",
                        new StringBody(userDTO.getDay(),
                                ContentType.create("application/x-www-form-urlencoded", StandardCharsets.UTF_8)))
                .addPart("appbundle_personal_horoscope[dateBirth][month]",
                        new StringBody(userDTO.getMonth(),
                                ContentType.create("application/x-www-form-urlencoded", StandardCharsets.UTF_8)))
                .addPart("appbundle_personal_horoscope[dateBirth][year]",
                        new StringBody(userDTO.getYear(),
                                ContentType.create("application/x-www-form-urlencoded", StandardCharsets.UTF_8)))
                .addPart("appbundle_personal_horoscope[gender]",
                        new StringBody(userDTO.getGender(),
                                ContentType.create("application/x-www-form-urlencoded", StandardCharsets.UTF_8)))

                .build();
        String answer = null;
        /**
         * Use pipeline streams to write the encoded data directly to the network
         * instead of caching it in memory. Because Multipart request bodies contain
         * files, they can cause memory overflows if cached in memory.
         */

        try {
            Pipe pipe = Pipe.open();
            // Pipeline streams must be used in a multi-threaded environment. Using one
            // thread for simultaneous reads and writes can lead to deadlocks.
            new Thread(() -> {
                try (OutputStream outputStream = Channels.newOutputStream(pipe.sink())) {
                    // Write the encoded data to the pipeline.
                    httpEntity.writeTo(outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder(new URI(horConfig.getUrl()))
                    // The Content-Type header is important, don't forget to set it.
                    .header("Content-Type", httpEntity.getContentType().getValue())
                    // Reads data from a pipeline stream.
                    .POST(BodyPublishers.ofInputStream(() -> Channels.newInputStream(pipe.source()))).build();

            HttpResponse<String> responseBody = httpClient.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));

            answer = responseBody.body();
        }catch (IOException | URISyntaxException | InterruptedException  exception){
            System.out.println(exception.getMessage());
        }

        HoroscopeDTO horoscopeDTO = ParserHoroscope.createHoroscopeInstance(answer);

        horoscopeDTO = translator.translateHoroscope(horoscopeDTO);

        return horoscopeDTO;
    }
}
