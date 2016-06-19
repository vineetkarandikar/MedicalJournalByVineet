package com.open.medjournal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose An entity UserRole composed by two fields (emailId and roleName).The Entity annotation
 *          indicates that this class is a JPA entity. The Table annotation specifies the name for
 *          the table in the database.
 *
 */

@Entity
@Table(name = "role")
public class UserRole implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  @Column(name = "email_id")
  private String emailId;

  @Column(name = "role_name")
  private String roleName;

  public UserRole() {

  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  @Override
  public String getAuthority() {
    return roleName;
  }

}
