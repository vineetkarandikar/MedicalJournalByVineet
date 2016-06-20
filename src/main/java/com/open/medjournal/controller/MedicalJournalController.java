package com.open.medjournal.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.open.medjournal.common.InternalAppConstant;
import com.open.medjournal.domain.MedicalJournalItem;
import com.open.medjournal.service.MedicalJournalItemService;

/**
 * @author vineetkarandikar@gmail.com
 * @createdDate 19/06/2016
 * @purpose This class acts as rest api controller medical journal activities.
 */

@RequestMapping("/medical/journal/v1")
@RestController
public class MedicalJournalController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MedicalJournalController.class);

  @Inject
  MedicalJournalItemService MedicalJournalItemService;

  @RequestMapping(value = "/savemedicalitem", method = RequestMethod.POST)
  @ResponseBody
  public MedicalJournalItem saveMedicalJournalItem(
      @RequestBody MedicalJournalItem medicalJournalItem) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " saving of medical journal item started " + InternalAppConstant.LOGGER_MESSAGE_END);
    return MedicalJournalItemService.saveMedicalJournalItem(medicalJournalItem);
  }

  @RequestMapping(value = "/getmedicalitems", method = RequestMethod.GET)
  @ResponseBody
  public List<MedicalJournalItem> getMedicalJournalItems(
      @RequestHeader("description") String description, @RequestHeader("pageNumber") int pageNumber) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " retrieving of medical journal items started based on description" + InternalAppConstant.LOGGER_MESSAGE_END);
    return MedicalJournalItemService.getMedicalJournalItemsByDescription(description, pageNumber);
  }

  @RequestMapping(value = "/getmedicalitems", method = RequestMethod.GET)
  @ResponseBody
  public List<MedicalJournalItem> getMedicalJournalItems(@RequestHeader("pageNumber") int pageNumber) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " retrieving of medical journal items started " + InternalAppConstant.LOGGER_MESSAGE_END);
    return MedicalJournalItemService.getAllMedicalJournalItems(pageNumber);
  }

  @RequestMapping(value = "/getmedicalitembyid", method = RequestMethod.GET)
  @ResponseBody
  public MedicalJournalItem getMedicalJournalItemsById(
      @RequestHeader("medJournalItemId") long medJournalItemId) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " retrieving of medical journal item started based on id" + InternalAppConstant.LOGGER_MESSAGE_END);
    return MedicalJournalItemService.getMedicalJournalItemById(medJournalItemId);
  }

  @RequestMapping(value = "/update/medicalitem", method = RequestMethod.GET)
  @ResponseBody
  public void updateMedicalJournalItem(@RequestBody MedicalJournalItem medicalJournalItem) {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " updating of medical journal item started " + InternalAppConstant.LOGGER_MESSAGE_END);
    MedicalJournalItemService.updateMedicalJournalItem(medicalJournalItem);
  }

  @RequestMapping(value = "/savefiles", method = RequestMethod.POST)
  @ResponseBody
  public void saveFilesMedicalJournalItem(@RequestHeader("medJournalItemId") long medJournalItemId,
      @RequestParam("file") MultipartFile[] files) throws IOException {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " saving of medical journal pdf documents started "
        + InternalAppConstant.LOGGER_MESSAGE_END);
    MedicalJournalItemService.saveFiles(files, medJournalItemId);
  }

  @RequestMapping(value = "/delete/medicaljournalitem", method = RequestMethod.DELETE)
  @ResponseBody
  public void deleteMedicalJournalItem(@RequestHeader("medJournalItemId") long medJournalItemId)
      throws IOException {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " deleteing of medical journal documents started "
        + InternalAppConstant.LOGGER_MESSAGE_END);
    MedicalJournalItemService.deleteMedicalJournalItem(medJournalItemId);
  }

  @RequestMapping(value = "/updatefiles", method = RequestMethod.PUT)
  @ResponseBody
  public void updateFilesMedicalJournalItem(
      @RequestHeader("medJournalItemId") long medJournalItemId,
      @RequestParam("file") MultipartFile[] files) throws IOException {
    LOGGER.debug(InternalAppConstant.LOGGER_MESSAGE_START
        + " updating pdf file  of medical journal documents started "
        + InternalAppConstant.LOGGER_MESSAGE_END);
    MedicalJournalItemService.updatePdfFiles(files, medJournalItemId);
  }

}
