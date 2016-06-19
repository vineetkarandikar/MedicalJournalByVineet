package com.open.medjournal.domain.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.open.medjournal.domain.UserRole;

/**
 * 
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose A DAO for the entity UserRole is simply created by extending the CrudRepository
 *          interface provided by spring. The following methods are some of the ones available from
 *          such interface: save, delete, deleteAll, findOne and findAll. The magic is that such
 *          methods must not be implemented, and moreover it is possible create new query methods
 *          working only by defining their signature!
 *
 */

public interface UserRoleRepository extends CrudRepository<UserRole, Serializable> {

  public List<UserRole> getRolesByUserName(String emailId);

}
