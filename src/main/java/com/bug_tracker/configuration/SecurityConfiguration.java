package com.bug_tracker.configuration;

import com.bug_tracker.controller.AfterLoginController;
import com.bug_tracker.security.UserDetailsServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    UserDetailsServiceJPA userDetailService;

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        DelegatingPasswordEncoder passworEncoder = new DelegatingPasswordEncoder(
                "bcrypt", encoders);
        return passworEncoder;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new AfterLoginController();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
        //  .passwordEncoder(delegatingPasswordEncoder());

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin();
        // http
        //  .csrf().disable().cors().and().authorizeRequests().and()
        // .antMatchers(HttpMethod.GET,
        // "/bug-track-react/public/index*", "/static/**", "/*.js", "/*.json", "/*.ico")
        // .permitAll()
        //.anyRequest().authenticated()
        // .and()
        //      .formLogin()//.loginPage("/bug-track-react/public/index.html")
        //  .loginProcessingUrl("/perform_login")
        //   .permitAll()
        // .defaultSuccessUrl("/logine.html")
        //  .defaultSuccessUrl("/homepage.html",true)
        //  .failureUrl("/index.html?error=true")

        ;
    }

    @Autowired
    public void setUserDetailService(UserDetailsServiceJPA userDetailService) {
        this.userDetailService = userDetailService;
    }
}
