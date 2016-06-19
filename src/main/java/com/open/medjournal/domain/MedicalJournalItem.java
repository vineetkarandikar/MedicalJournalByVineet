package com.open.medjournal.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose An entity MedicalJournalItem composed by six fields (id,
 *          description,updateBy,createdBy,createdDate and updatedDate). The Entity annotation
 *          indicates that this class is a JPA entity. The Table annotation specifies the name for
 *          the table in the database.
 */

@Entity
@Table(name = "medical_journal_item")
public class MedicalJournalItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "med_journal_item_id")
  private long medJournalItemId;

  private String description;

  @Column(name = "created_date")
  private Date createdDate;

  @Column(name = "updated_date")
  private Date updatedDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "updated_by")
  private String updatedBy;

  public MedicalJournalItem() {

  }

  public long getMedJournalItemId() {
    return medJournalItemId;
  }

  public void setMedJournalItemId(long medJournalItemId) {
    this.medJournalItemId = medJournalItemId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

}
