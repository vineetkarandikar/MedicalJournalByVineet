package com.open.medjournal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.open.medjournal.domain.MedicalJournalItem;

public interface MedicalJournalItemService {

  void saveMedicalJournalItem(MedicalJournalItem medicalJournalItem);

  void updateMedicalJournalItem(MedicalJournalItem medicalJournalItem);
  
  void deleteMedicalJournalItem(long medJournalItemId);

  List<MedicalJournalItem> viewMedicalJournalItemsByDescription(String description,int pageNumber);
  
  List<MedicalJournalItem> getAllMedicalJournalItems(int pageNumber);

  void saveFiles(MultipartFile[] files,long medJournalItemId) throws IOException;

  void validatePDFFile(MultipartFile pdfFile) throws IOException;

  void deletePdfFilesFromDirectory(List<String> fileNames, long medJournalItemId);

  void deletePdfFile(String fileName, long medJournalItemId);

}
