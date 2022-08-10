package ru.neoflex.educationplatform.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
            .cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/users/login").permitAll()
            .antMatchers("/users/registration").permitAll()
            .antMatchers("/courses/available").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/v2/api-docs/**")
        .antMatchers("/v3/api-docs/**")
        .antMatchers("/swagger-resources/**")
        .antMatchers("/swagger-ui.html")
        .antMatchers("/swagger-ui/**")
        .antMatchers("/swagger-ui/index.html")
        .antMatchers("/configuration/**")
        .antMatchers("/webjars/**")
        .antMatchers("/public");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
