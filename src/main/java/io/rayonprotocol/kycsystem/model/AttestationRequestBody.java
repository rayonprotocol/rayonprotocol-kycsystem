package io.rayonprotocol.kycsystem.model;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AttestationRequestBody {
  // user address
  @NotBlank
  private String userId;
  
  @NotBlank
  private String userAuth;
}