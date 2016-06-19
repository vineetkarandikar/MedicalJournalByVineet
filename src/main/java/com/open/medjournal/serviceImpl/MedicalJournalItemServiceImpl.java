package com.open.medjournal.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.exception.SyntaxValidationException;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.open.medjournal.common.InternalAppConstant;
import com.open.medjournal.common.MedJournalException;
import com.open.medjournal.domain.MedicalJournalItem;
import com.open.medjournal.domain.repository.MedicalJournalItemRepository;
import com.open.medjournal.service.MedicalJournalItemService;
import com.open.medjournal.service.MessageByLocaleService;

/**
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose This class implements MedJournalItemService interface and perform CRUD customized
 *          operations related service activities.
 */

public class MedicalJournalItemServiceImpl implements MedicalJournalItemService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MedicalJournalItemServiceImpl.class);

  @Value("${pdf.dir.path}")
  private String pdfDirPath;

  private static final int PAGE_SIZE = 10;

  @Inject
  private MedicalJournalItemRepository medicalJournalItemRepository;

  @Inject
  private MessageByLocaleService messageByLocaleService;

  @Override
  public void validatePDFFile(MultipartFile pdfFile) throws IOException {
    try {
      LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START + " PDF File validation starts "
          + InternalAppConstant.LOGGER_MESSAGE_END);
      File convFile = new File(pdfFile.getOriginalFilename());
      pdfFile.transferTo(convFile);
      PreflightParser parser = new PreflightParser(convFile);
      parser.parse();
      /*
       * Once the syntax validation is done, the parser can provide a PreflightDocument (that
       * inherits from PDDocument) This document process the end of PDF/A validation.
       */
      PreflightDocument document = parser.getPreflightDocument();
      document.validate();
      // Get validation result
      ValidationResult result = document.getResult();
      LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START + " PDF File success " + result
          + InternalAppConstant.LOGGER_MESSAGE_END);
      document.close();
    } catch (SyntaxValidationException e) {
      /*
       * the parse method can throw a SyntaxValidationException if the PDF file can't be parsed. In
       * this case, the exception contains an instance of ValidationResult
       */
      LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + " PDF File validation fails "
          + e.getMessage() + InternalAppConstant.LOGGER_MESSAGE_END);
      String message = messageByLocaleService.getMessage("pdf.validation.fail");
      throw new MedJournalException(message);
    }

  }

  @Override
  public void saveFiles(MultipartFile[] files, long medJournalItemId) throws IOException {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " save operation for PDF Files started  " + InternalAppConstant.LOGGER_MESSAGE_END);
    String fileName = null;
    String msg = "";
    if (files != null && files.length > 0) {
      for (int i = 0; i < files.length; i++) {
        fileName = files[i].getOriginalFilename();
        validatePDFFile(files[i]); // PDF validation
        byte[] bytes = files[i].getBytes();
        File file = new File(pdfDirPath + "/" + medJournalItemId + "/" + fileName);
        file.getParentFile().mkdirs();
        BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(file));
        buffStream.write(bytes);
        buffStream.close();
        msg += messageByLocaleService.getMessage("file.upload.success") + fileName;
        LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
            + " save operation for PDF Files completed  " + msg
            + InternalAppConstant.LOGGER_MESSAGE_END);
      }
    } else {
      throw new MedJournalException(messageByLocaleService.getMessage("file.upload.empty"));
    }
  }


  @Override
  public void saveMedicalJournalItem(MedicalJournalItem medicalJournalItem) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " save operation for Medical journal item started  "
        + InternalAppConstant.LOGGER_MESSAGE_END);
    MedicalJournalItem medicalJournalItemIn = medicalJournalItem;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    medicalJournalItemIn.setCreatedBy(authentication.getName());
    medicalJournalItemIn.setUpdatedBy(authentication.getName());
    medicalJournalItemIn.setCreatedDate(new Date(System.currentTimeMillis()));
    medicalJournalItemIn.setUpdatedDate(new Date(System.currentTimeMillis()));
    medicalJournalItemRepository.save(medicalJournalItem);
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " save operation for Medical journal item completed  "
        + InternalAppConstant.LOGGER_MESSAGE_END);
  }

  @Override
  public void updateMedicalJournalItem(MedicalJournalItem medicalJournalItem) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " update operation for Medical journal item started  "
        + InternalAppConstant.LOGGER_MESSAGE_END);
    MedicalJournalItem medicalJournalItemIn = medicalJournalItem;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    medicalJournalItemIn.setUpdatedBy(authentication.getName());
    medicalJournalItemIn.setUpdatedDate(new Date(System.currentTimeMillis()));
    medicalJournalItemRepository.save(medicalJournalItem);
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " update operation for Medical journal item completed  "
        + InternalAppConstant.LOGGER_MESSAGE_END);
  }

  @Override
  public void deleteMedicalJournalItem(long medJournalItemId) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " delete operation for Medical journal item started  "
        + InternalAppConstant.LOGGER_MESSAGE_END);
    medicalJournalItemRepository.delete(medJournalItemId);
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " delete operation for Medical journal item completed  "
        + InternalAppConstant.LOGGER_MESSAGE_END);
  }

  @Override
  public List<MedicalJournalItem> viewMedicalJournalItemsByDescription(String description,
      int pageNumber) {
    LOGGER
        .debug(InternalAppConstant.LOGGER_MESSAGE_START
            + " retrieve operation for Medical journal item started based on description and page number "
            + InternalAppConstant.LOGGER_MESSAGE_END);
    PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE);
    return medicalJournalItemRepository.getMedicalJournalItemsByDescription(description, request);
  }

  @Override
  public List<MedicalJournalItem> getAllMedicalJournalItems(int pageNumber) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " retrieve operation for Medical journal item started based on page number "
        + InternalAppConstant.LOGGER_MESSAGE_END);
    PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE);
    return medicalJournalItemRepository.findAll(request).getContent();
  }

  @Override
  public void deletePdfFilesFromDirectory(List<String> fileNames, long medJournalItemId) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " delete operation for files started in directory "
        + InternalAppConstant.LOGGER_MESSAGE_END);
    File filePath = new File(pdfDirPath + "/" + medJournalItemId);
    File[] files = filePath.listFiles();
    for (File file : files) {
      if (file.isFile() && file.exists()) {
        file.delete();
        LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
            + " delete operation for files completed in directory "
            + InternalAppConstant.LOGGER_MESSAGE_END);
      } else {
        throw new MedJournalException(messageByLocaleService.getMessage("file.delete.ops.fail"));
      }
    }
  }

  @Override
  public void deletePdfFile(String fileName, long medJournalItemId) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START + " delete operation for files started"
        + InternalAppConstant.LOGGER_MESSAGE_END);
    File file = new File(pdfDirPath + "/" + medJournalItemId + "/" + fileName);
    if (file.isFile() && file.exists()) {
      file.delete();
      LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
          + " delete operation for file completed" + InternalAppConstant.LOGGER_MESSAGE_END);
    } else {
      throw new MedJournalException(messageByLocaleService.getMessage("file.delete.ops.fail"));
    }
  }
}
