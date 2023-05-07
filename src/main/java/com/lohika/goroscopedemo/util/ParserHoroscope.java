package com.lohika.goroscopedemo.util;

import com.lohika.goroscopedemo.dto.HoroscopeDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ParserHoroscope {

    public static HoroscopeDTO createHoroscopeInstance(String answer) {
        HoroscopeDTO horoscopeDTO = new HoroscopeDTO();
        Document document = Jsoup.parse(answer);
        Element title = document.selectFirst("title");
        horoscopeDTO.setTitle(title.text().trim());
        String[] items = new String[6];

        Elements elements = document.select(".horoscope_text");

        for (int i = 0; i < elements.size(); i++) {
            items[i] = elements.get(i).text().trim();
            if (items[i].contains("Подробная информация о знаке")) {
                String[] splits = items[i].split("Подробная информация о знаке");
                items[i] = splits[0].trim();
            }
        }
        horoscopeDTO.setIntroduction(items[0]);
        horoscopeDTO.setMoonDesc(items[1]);
        horoscopeDTO.setMonthDesc(items[2]);
        horoscopeDTO.setYearDesc(items[3]);
        horoscopeDTO.setBodyInfo(items[4]);
        horoscopeDTO.setSign(getSignFromIntroduction(horoscopeDTO.getIntroduction()));
        return horoscopeDTO;
    }

    private static String getSignFromIntroduction(String introduction) {
        StringBuilder result = new StringBuilder();
        for (Signs sign : Signs.values()) {
            if (introduction.contains(String.valueOf(sign))) {
                result.append(sign);
                break;
            }
        }
        return result.toString().toLowerCase();
    }

    private static enum Signs {
        Козерог,
        Водолей,
        Рыбы,
        Овен,
        Телец,
        Близнецы,
        Рак,
        Лев,
        Дева,
        Весы,
        Скорпион,
        Стрелец
    }
}
