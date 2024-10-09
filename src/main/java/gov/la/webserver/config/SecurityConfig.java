package gov.la.webserver.config;

import gov.la.webserver.user.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    final UserLoginService userLoginService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults())
                .formLogin(e->e.successForwardUrl("/main"))
                .userDetailsService(userLoginService)
                .authorizeHttpRequests(authorizeRequest->{
                    authorizeRequest
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/main/logout")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/users/**")).permitAll()
                            .anyRequest().authenticated();
                })
                .logout((logout)->{
                    logout.logoutSuccessUrl("/main/logout")
                            .deleteCookies("JSESSIONID", "remember-me");
                })
                .headers(headers ->{
                    headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                } );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
