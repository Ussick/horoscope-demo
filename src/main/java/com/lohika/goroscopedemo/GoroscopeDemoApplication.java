package com.lohika.goroscopedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.data.util.Pair;

import java.util.Arrays;

@SpringBootApplication
public class GoroscopeDemoApplication {
//	public final ApplicationContext context;

	public GoroscopeDemoApplication(ApplicationContext context) {
//		this.context = context;
//		System.out.println("********************");
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
//		System.out.println("********************");
//		System.out.println(context);
//		System.out.println("********************");

	}

	public static void main(String[] args) {
		SpringApplication.run(GoroscopeDemoApplication.class, args);
	}

}
