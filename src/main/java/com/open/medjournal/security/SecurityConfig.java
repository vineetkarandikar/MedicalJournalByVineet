package com.open.medjournal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose This class is responsible for application security specific settings.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomAuthenticationProvider authenticationProvider;

  @Autowired
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Autowired
  private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

  /**
   * This method configures application rest entry end points.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**", "*/nonsecure/*").permitAll()
        .and().csrf().disable().exceptionHandling()
        .authenticationEntryPoint(restAuthenticationEntryPoint).and().authorizeRequests()
        .anyRequest().fullyAuthenticated().and().formLogin()
        .successHandler(authenticationSuccessHandler)
        .failureHandler(new SimpleUrlAuthenticationFailureHandler()).and().logout();

  }

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);
  }

  @Bean
  public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
    return new MySavedRequestAwareAuthenticationSuccessHandler();
  }

  @Bean
  public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
    return new SimpleUrlAuthenticationFailureHandler();
  }

  /**
   * This methods is used for by-passing security for mentioned resources in application.
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/user/v1/nonsecure/**"); // #3
  }

}
