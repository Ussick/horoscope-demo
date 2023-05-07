package com.lohika.goroscopedemo.service;

import com.lohika.goroscopedemo.dto.HoroscopeDTO;
import com.lohika.goroscopedemo.entities.Horoscope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class Translator {

    @Value("${translator.url}")
    private String urlSource;

    @Value("${translator.langFrom}")
    private String langfrom;

    @Value("${translator.langTo}")
    private String langTo;

    private String translate(String langFrom, String langTo, String text) throws IOException {
        if (text.length() < 1100) {
            return getText(langFrom, langTo, text).replaceAll("&#39;","'");
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] strings = text.split("\\.");
        int i = strings.length / 2;
        int j = strings.length - i;
        for (int k = 0; k < i; k++) {
            stringBuilder.append(strings[k] + ". ");
        }
        String temp1 = getText(langFrom, langTo, stringBuilder.toString());
        stringBuilder.setLength(0);

        for (int k = j; k < strings.length; k++) {
            stringBuilder.append(strings[k] + ". ");
        }
        String temp2 = getText(langFrom, langTo, stringBuilder.toString());
        return temp1.concat(temp2).trim().replaceAll("&#39;","'");
    }

    private String getText(String langFrom, String langTo, String text) throws IOException {
        String urlStr = urlSource +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public HoroscopeDTO translateHoroscope(HoroscopeDTO horoscopeDTO) {

        try {

            ExecutorService executor = Executors.newFixedThreadPool(7);
            List<Callable<String>> tasks = new ArrayList<>();
            tasks.add(()->translate(langfrom, langTo, horoscopeDTO.getTitle()));
            tasks.add(()->translate(langfrom, langTo, horoscopeDTO.getIntroduction()));
            tasks.add(()->translate(langfrom, langTo, horoscopeDTO.getMoonDesc()));
            tasks.add(()->translate(langfrom, langTo, horoscopeDTO.getMonthDesc()));
            tasks.add(()->translate(langfrom, langTo, horoscopeDTO.getYearDesc()));
            tasks.add(()->translate(langfrom, langTo, horoscopeDTO.getBodyInfo()));
            tasks.add(()->translate(langfrom, langTo, horoscopeDTO.getSign()));

            List<Future<String>> futures = executor.invokeAll(tasks);
            executor.shutdown();
            List <String> resultsOfTranslation = new ArrayList<>();
            for (Future<String>future:futures){
                resultsOfTranslation.add(future.get());
            }

            horoscopeDTO.setTitle(resultsOfTranslation.get(0));
            horoscopeDTO.setIntroduction(resultsOfTranslation.get(1));
            horoscopeDTO.setMoonDesc(resultsOfTranslation.get(2));
            horoscopeDTO.setMonthDesc(resultsOfTranslation.get(3));
            horoscopeDTO.setYearDesc(resultsOfTranslation.get(4));
            horoscopeDTO.setBodyInfo(resultsOfTranslation.get(5));
            horoscopeDTO.setSign(resultsOfTranslation.get(6));


        } catch ( InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return  horoscopeDTO;
    }
}
