package com.open.medjournal.service;

import com.open.medjournal.domain.User;


public interface UserService {

  public User getUserDetailsByEmailId(String emailId);

}
