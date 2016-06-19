package com.open.medjournal.serviceImpl;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.open.medjournal.service.MessageByLocaleService;

@Service
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

  @Inject
  private MessageSource messageSource;

  @Override
  public String getMessage(String id) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(id, null, locale);
  }
}
