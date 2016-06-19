package com.open.medjournal.common;

/**
 * @author vineetkarandikar@gmail.com
 * @CreatedDate 19/06/2016
 * @Purpose This class is a customized runtime exception for medical journal.
 */
public class MedJournalException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public MedJournalException() {
    super();
  }

  public MedJournalException(String s) {
    super(s);
  }

  public MedJournalException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public MedJournalException(Throwable throwable) {
    super(throwable);
  }
}
