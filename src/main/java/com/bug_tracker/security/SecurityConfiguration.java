package com.bug_tracker.security;

import com.bug_tracker.controller.AfterLoginController;
import com.bug_tracker.security.filter.InitialAuthenticationFilter;
import com.bug_tracker.security.provider.AuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private UserDetailsServiceJPA userDetailService;
    private InitialAuthenticationFilter initialAuthenticationFilter;
    private AuthenticationProviderService authenticationProvider;

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
        return new AfterLoginController();
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
        http.addFilterAt(
                initialAuthenticationFilter,
                BasicAuthenticationFilter.class);

        http.authorizeRequests()
                .anyRequest().authenticated();

//        http
//                .authorizeRequests()
//                .mvcMatchers("/users/{id}")
//                .access("isAuthenticated() and @securityConfiguration.checkUserId(authentication, #id)")
//                .mvcMatchers(HttpMethod.POST, "/").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.DELETE, "/projects/{id}").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.PUT, "/projects/{id}").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and().formLogin();
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
        //  .failureUrl("/index.html?error=true");
    }

    public boolean checkUserId(Authentication authentication, long id) {
        long currentUserId = ((UserSecurity) authentication.getPrincipal()).getUserId();
        return id == currentUserId;
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }


    @Autowired
    public void setInitialAuthenticationFilter(InitialAuthenticationFilter initialAuthenticationFilter) {
        this.initialAuthenticationFilter = initialAuthenticationFilter;
    }

    @Autowired
    public void setAuthenticationProvider(AuthenticationProviderService authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

}
