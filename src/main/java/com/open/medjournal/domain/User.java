package com.open.medjournal.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose An entity User composed by three fields (emailId,name,password).The Entity annotation
 *          indicates that this class is a JPA entity. The Table annotation specifies the name for
 *          the table in the database.
 */

@Entity
@Table(name = "user")
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "email_id")
  private String emailId;

  @Column(name = "name")
  private String Name;

  @Column(name = "password")
  private String password;

  @Transient
  private List<UserRole> authorities;

  public User() {

  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<UserRole> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<UserRole> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return emailId;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return false;
  }

}
