package com.open.medjournal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.open.medjournal.domain.MedicalJournalItem;

public interface MedicalJournalItemService {

  MedicalJournalItem saveMedicalJournalItem(MedicalJournalItem medicalJournalItem);

  void updateMedicalJournalItem(MedicalJournalItem medicalJournalItem);
  
  void deleteMedicalJournalItem(long medJournalItemId);

  List<MedicalJournalItem> getMedicalJournalItemsByDescription(String description,int pageNumber);
  
  List<MedicalJournalItem> getAllMedicalJournalItems(int pageNumber);

  void saveFiles(MultipartFile[] files,long medJournalItemId) throws IOException;

  void validatePDFFile(MultipartFile pdfFile) throws IOException;

  void deletePdfFilesFromDirectory(long medJournalItemId);

  void deletePdfFile(String fileName, long medJournalItemId);
  
  void updatePdfFiles(MultipartFile[] files, long medJournalItemId) throws IOException;

  MedicalJournalItem getMedicalJournalItemById(long medJournalItemId);

}
