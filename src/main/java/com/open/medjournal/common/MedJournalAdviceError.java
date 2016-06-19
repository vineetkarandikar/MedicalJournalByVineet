package com.open.medjournal.common;

import org.springframework.stereotype.Component;

/**
 * @author vineetkarandikar@gmail.com
 * @CreatedDate 19/06/2016
 * @Purpose This class is common for showing all error/exception messages.
 */

@Component
public class MedJournalAdviceError {

  private String errormessage;

  private String trace = "NA";

  public String getErrormessage() {
    return errormessage;
  }

  public void setErrormessage(String errormessage) {
    this.errormessage = errormessage;
  }

  public String getTrace() {
    return trace;
  }

  public void setTrace(String trace) {
    this.trace = trace;
  }

}
