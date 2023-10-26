package com.generali.mailservice.security;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  @Value("${mail-service.username}")
  private String mailServiceUsername;
  @Value("${mail-service.password}")
  private String mailServicePassword;

  private final JwtFilter jwtFilter;

  @Bean
  public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests(request -> request
      .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/api/mails")).hasAuthority("mail-service:read")
      .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/api/mails")).hasAuthority("mail-service:write")
      .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
      .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
      .requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui.html")).permitAll()
      .requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()
      .anyRequest().authenticated());

    httpSecurity.httpBasic(basic -> Customizer.withDefaults());
    httpSecurity.formLogin(form -> form.disable());
    httpSecurity.headers(headers -> headers.frameOptions(options -> options.disable()));
    httpSecurity.csrf(csrf -> csrf.disable());
    httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));

    httpSecurity.addFilterBefore(jwtFilter, BasicAuthenticationFilter.class);

    return httpSecurity.build();
  }

  @Bean
  public UserDetailsService users() {
    UserDetails mailService = User.withDefaultPasswordEncoder()
      .username(mailServiceUsername)
      .password(mailServicePassword)
      .authorities("mail-service:write")
      .build();
    return new InMemoryUserDetailsManager(mailService);
  }
}
