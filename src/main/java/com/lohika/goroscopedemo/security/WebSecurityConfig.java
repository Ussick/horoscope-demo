package com.lohika.goroscopedemo.security;

import com.lohika.goroscopedemo.repository.UserRepository;
import com.lohika.goroscopedemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // {
    public static final String basicurl = "/v1/horoscope";

    @Autowired
    CustomUserDetailService customUserDetailService;
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests()
                .mvcMatchers(basicurl + "/registration/**",
                        basicurl + "/",
                        basicurl + "/activate/*",
                        basicurl + "/main",
                        basicurl + "/impersonal/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(basicurl + "/login").usernameParameter("login").defaultSuccessUrl(basicurl + "/main_user",true)
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "webjars/**", "/js", "/h2-console/**");
    }

}