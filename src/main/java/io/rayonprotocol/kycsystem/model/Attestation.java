package io.rayonprotocol.kycsystem.model;
import lombok.Data;

@Data
public class Attestation {
  private String attesterId;
  
  private String authHash;

  private String r;

  private String s;

  private String v;
}