package io.rayonprotocol.kycsystem.model;

import java.util.Date;

import lombok.Getter;

public class ErrorDetails {
  @Getter
  private Date timestamp;

  @Getter
  private Integer status;

  @Getter
  private String error;

  @Getter
  private String message;
  
  @Getter
  private String details;

  public ErrorDetails(Date timestamp, Integer status, String error, String message, String details) {
    super();
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
    this.details = details;
  }
}
