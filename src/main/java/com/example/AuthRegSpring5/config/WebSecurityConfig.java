package com.example.AuthRegSpring5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, password, active from users where username = ?")
                .authoritiesByUsernameQuery("select u.username, ur.name_role from users u inner join role ur on u.role_id = ur.id_role where u.username = ?")
                .and().authenticationEventPublisher(authenticationEventPublisher());
    }

    @Bean
    public DefaultAuthenticationEventPublisher authenticationEventPublisher() {
        return new DefaultAuthenticationEventPublisher();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/registration", "/style.css").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/tickets")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable()
                .cors().disable();
    }

     @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}