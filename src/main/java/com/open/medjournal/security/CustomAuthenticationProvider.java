package com.open.medjournal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.open.medjournal.service.AuthenticationService;

/**
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose -This class is responsible for custom authentication and role building for a user
 *          requesting a login.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  AuthenticationService authenticationService;

  /**
   * This method is used to authenticate user and also inserts roles to user session successfully
   * logged in.
   */
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String emailId = authentication.getName();
    String password = (String) authentication.getCredentials();
    return authenticationService.validateCredentials(emailId, password);
  }

  public boolean supports(Class<?> arg0) {
    return true;
  }
}
