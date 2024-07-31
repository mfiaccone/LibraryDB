package org.perscholas.librarydb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurity {

    @Bean(name="passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.csrf(csrf -> csrf.disable());

        // this section says allow all pages EXCEPT the ones that are in the AntPthRequestMatcher
        // anything in AntPathRequestMatcher will require the user to be authenticated
        http.authorizeRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/admin/**")).authenticated()
//                        new AntPathRequestMatcher("/user/**")).authenticated()
                .anyRequest().permitAll();

        // the loginPage parameter is the actual URL of the login page
        // the loginProcessingUrl is the URL that the form will submit to
        http.formLogin(formLogin -> formLogin
                // this is a controller method URL for displaying the login page (this can be any url but has to match JSP form action)
                .loginPage("/user/loginPageUrl")

                .loginProcessingUrl("/user/loginProcessingURL"));

        http.logout(formLogout -> formLogout
                .invalidateHttpSession(true)
                // this is the URL that will log a user out
                .logoutUrl("/login/logout")
                .logoutSuccessUrl("/"));

        return http.build();
    }


}