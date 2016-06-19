package com.open.medjournal.domain.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.open.medjournal.domain.User;

/**
 * 
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose A DAO for the entity User is simply created by extending the CrudRepository interface
 *          provided by spring. The following methods are some of the ones available from such
 *          interface: save, delete, deleteAll, findOne and findAll. The magic is that such methods
 *          must not be implemented, and moreover it is possible create new query methods working
 *          only by defining their signature!
 *
 */


public interface UserRepository extends CrudRepository<User, Serializable> {

  // get user for given email id.
  public User getUserByEmailId(String emailId);

}
