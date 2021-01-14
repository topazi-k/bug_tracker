package com.bug_tracker.security;

import com.bug_tracker.security.filter.InitialAuthenticationFilter;
import com.bug_tracker.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
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

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceJPA userDetailService;
    private InitialAuthenticationFilter initialAuthenticationFilter;
    //    private AuthenticationProviderService authenticationProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(UserDetailsServiceJPA userDetailsServiceJPA) {
        this.userDetailService = userDetailsServiceJPA;
    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(
                "bcrypt", encoders);

    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new SuccessAuthenticationHandler();
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
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers("/users/{id}")
                .access("isAuthenticated() and @securityConfiguration.checkUserId(authentication, #id)")
                .mvcMatchers(POST, "/projects").hasRole("ADMIN")
                .mvcMatchers(POST, "/projects/{id}").hasRole("ADMIN")
                .mvcMatchers(DELETE, "/projects/{id}").hasRole("ADMIN")
                .mvcMatchers(PUT, "/projects/{id}").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.addFilter(
                initialAuthenticationFilter)
                .addFilterAfter(jwtAuthenticationFilter, InitialAuthenticationFilter.class);

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    public boolean checkUserId(Authentication authentication, long id) {
        JwtAuthentication authentication1 = (JwtAuthentication) authentication.getPrincipal();
        return id == authentication1.getId();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(delegatingPasswordEncoder());
    }


    @Autowired
    public void setInitialAuthenticationFilter(InitialAuthenticationFilter initialAuthenticationFilter) {
        this.initialAuthenticationFilter = initialAuthenticationFilter;
    }

    @Autowired
    public void setJwtAuthenticationFilter(JwtAuthenticationFilter filter) {
        this.jwtAuthenticationFilter = filter;
    }

//    At this point no need to use custom auth provider( it does same job as default)
//    But when I will create additional sms or email authentication it will need


//    @Autowired
//    public void setAuthenticationProvider(AuthenticationProviderService authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }

}
