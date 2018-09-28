package io.rayonprotocol.kycsystem.exception;

public class CredentialNotInitiatedException extends RuntimeException {
  private static final String CREDENTIAL_NOT_INITIATED = "Credential not initiated";

  public CredentialNotInitiatedException() {
    super(CREDENTIAL_NOT_INITIATED);
  }
}