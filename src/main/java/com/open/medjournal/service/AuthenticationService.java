package com.open.medjournal.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public interface AuthenticationService {
  public UsernamePasswordAuthenticationToken validateCredentials(String emailId, String password);
}
