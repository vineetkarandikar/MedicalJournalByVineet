package com.open.medjournal.common;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author vineetkarandikar@gmail.com
 * @since 19/06/2016
 * @purpose This class is a common intercepter for error/exception which arise from any point in
 *          medical journal exception.
 */

@ControllerAdvice
public class MedJournalExceptionControllerAdvice {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MedJournalExceptionControllerAdvice.class);

  @Autowired
  MedJournalAdviceError medJournalError;

  /**
   * This method intercepts exception of type MedJournalException
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MedJournalException.class)
  @ResponseBody
  public MedJournalAdviceError handleException(MedJournalException exception) {
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_STACK_TRACE
        + exception.getStackTrace().toString() + InternalAppConstant.LOGGER_MESSAGE_END);
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_CAUSE
        + exception.getMessage() + InternalAppConstant.LOGGER_MESSAGE_END);
    medJournalError.setErrormessage(exception.getMessage());
    return medJournalError;
  }


  /**
   * This method intercepts exception of type IOException
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(IOException.class)
  @ResponseBody
  public MedJournalAdviceError handleException(IOException exception) {
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_STACK_TRACE
        + exception.getStackTrace().toString() + InternalAppConstant.LOGGER_MESSAGE_END);
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_CAUSE
        + exception.getMessage() + InternalAppConstant.LOGGER_MESSAGE_END);
    medJournalError.setErrormessage(exception.getMessage());
    medJournalError.setTrace(exception.getCause().toString());
    return medJournalError;
  }

  /**
   * This method intercepts exception of type FileNotFoundException
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(FileNotFoundException.class)
  @ResponseBody
  public MedJournalAdviceError handleException(FileNotFoundException exception) {
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_STACK_TRACE
        + exception.getStackTrace().toString() + InternalAppConstant.LOGGER_MESSAGE_END);
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_CAUSE
        + exception.getMessage() + InternalAppConstant.LOGGER_MESSAGE_END);
    medJournalError.setErrormessage(exception.getMessage());
    medJournalError.setTrace(exception.getCause().toString());
    return medJournalError;
  }

  /**
   * This method intercepts exception of type NullPointerException
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(NullPointerException.class)
  @ResponseBody
  public MedJournalAdviceError handleException(NullPointerException exception) {
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_STACK_TRACE
        + exception.getStackTrace().toString() + InternalAppConstant.LOGGER_MESSAGE_END);
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_CAUSE
        + exception.getMessage() + InternalAppConstant.LOGGER_MESSAGE_END);
    medJournalError.setErrormessage(exception.getMessage());
    medJournalError.setTrace(exception.getCause().toString());
    return medJournalError;
  }

  /**
   * This method intercepts exception of type Exception
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public MedJournalAdviceError handleException(Exception exception) {
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_STACK_TRACE
        + exception.getStackTrace().toString() + InternalAppConstant.LOGGER_MESSAGE_END);
    LOGGER.error(InternalAppConstant.LOGGER_MESSAGE_START + InternalAppConstant.ERROR_CAUSE
        + exception.getMessage() + InternalAppConstant.LOGGER_MESSAGE_END);
    medJournalError.setErrormessage(exception.getMessage());
    medJournalError.setTrace(exception.getCause().toString());
    return medJournalError;
  }
}
