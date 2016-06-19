package com.open.medjournal.serviceImpl;



import java.util.Collection;

import javax.inject.Inject;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.open.medjournal.domain.User;
import com.open.medjournal.domain.repository.UserRepository;
import com.open.medjournal.domain.repository.UserRoleRepository;
import com.open.medjournal.service.AuthenticationService;
import com.open.medjournal.service.MessageByLocaleService;

/**
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose This class implements AuthenticationService interface and perform various authentication
 *          related service activities.
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  @Inject
  private UserRepository userRepository;

  @Inject
  private UserRoleRepository userRoleRepository;

  @Inject
  MessageByLocaleService messageByLocaleService;


  @Override
  public UsernamePasswordAuthenticationToken validateCredentials(String emailId, String password) {
    if (userRepository.getUserByEmailId(emailId).getPassword().equals(password)) {
      User user = userRepository.getUserByEmailId(emailId);
      user.setAuthorities(userRoleRepository.getRolesByUserName(emailId));
      Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
      return new UsernamePasswordAuthenticationToken(user, password, authorities);
    } else {
      String invalidLogin = messageByLocaleService.getMessage("user.login.invalid");
      throw new BadCredentialsException(invalidLogin);
    }
  }


}
