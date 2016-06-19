package com.open.medjournal.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.open.medjournal.domain.MedicalJournalItem;
import com.open.medjournal.service.MedicalJournalItemService;

@RequestMapping("/medical/journal/v1")
@RestController
public class MedicalJournalController {
  
  @Inject
  MedicalJournalItemService MedicalJournalItemService;
  
  @RequestMapping(value = "/save/medicalitem", method = RequestMethod.POST)
  @ResponseBody
  public MedicalJournalItem saveMedicalJournalItem(@RequestBody MedicalJournalItem medicalJournalItem)
}
}
