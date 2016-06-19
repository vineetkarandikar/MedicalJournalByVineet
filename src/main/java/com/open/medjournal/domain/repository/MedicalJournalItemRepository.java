package com.open.medjournal.domain.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.open.medjournal.domain.MedicalJournalItem;

/**
 * 
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose A DAO for the entity MedicalJournalItem is simply created by extending the
 *          CrudRepository interface provided by spring. The following methods are some of the ones
 *          available from such interface: save, delete, deleteAll, findOne and findAll. The magic
 *          is that such methods must not be implemented, and moreover it is possible create new
 *          query methods working only by defining their signature!
 *
 */

public interface MedicalJournalItemRepository extends JpaRepository<MedicalJournalItem, Long> {
  
  //Fetch list by description.
  @Query("select medicalJournalItem from MedicalJournalItem medicalJournalItem "
      + "where medicalJournalItem.description like %?1")
  public List<MedicalJournalItem> getMedicalJournalItemsByDescription(String description,Pageable pageable);
  
  public List<MedicalJournalItem> getAllMedicalJournalItems(Pageable pageable);

}
