package io.rayonprotocol.kycsystem.model;
import lombok.Data;

@Data
public class Attestation {
  private String messageHash;

  private String attesterAddress;

  private String r;

  private String s;

  private String v;
}