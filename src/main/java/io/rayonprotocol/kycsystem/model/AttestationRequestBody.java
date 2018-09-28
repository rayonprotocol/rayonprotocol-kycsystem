package io.rayonprotocol.kycsystem.model;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AttestationRequestBody {
  @NotBlank
  private String address;
  
  @NotBlank
  private String personalId;
}