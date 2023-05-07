package com.lohika.goroscopedemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

@Configuration
public class HoroscopeMVCconfig implements WebMvcConfigurer {
    public static final String basicurl = "/v1/horoscope";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .setCachePeriod(1800)
                .addResourceLocations("/WEB-INF/pdf/");
        registry.addResourceHandler("/static/**")
                .setCachePeriod(1800)
                .addResourceLocations("classpath:/static/");//ищет в дереве проекта

//        registry
//                .addResourceHandler("/resources/**")
//                .addResourceLocations("classpath:/resources/");

    }

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        bean.setOrder(0);
        return bean;
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(basicurl + "/login").setViewName("login");
    }
}
