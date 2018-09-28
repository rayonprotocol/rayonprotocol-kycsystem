package io.rayonprotocol.kycsystem.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CredentialRequestBody {
  @NotBlank
  private String password;
}