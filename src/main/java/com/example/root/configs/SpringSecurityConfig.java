package com.example.root.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .mvcMatchers("/", "/login", "/signup", "/user/login", "/user/signup").permitAll()
                .mvcMatchers(HttpMethod.GET,"/profile/*").permitAll()
                .anyRequest().authenticated()

            .and()

//        http.formLogin().loginPage("/user/login").
//                usernameParameter("email").
//                passwordParameter("password").permitAll();

                .formLogin().loginPage("/user/login")
                            .usernameParameter("email")
                            .passwordParameter("password").permitAll()

            .and()
                .logout().logoutSuccessUrl("/")

            .and()
                .rememberMe().userDetailsService(userDetailsService).tokenRepository(tokenRepository());

    }



    @Bean
    public PersistentTokenRepository tokenRepository() throws Exception {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/css/**", "/image/**", "/js/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
