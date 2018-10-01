package io.rayonprotocol.kycsystem.model;
import lombok.Data;

@Data
public class Attester {
  // attester address
  private String attesterId;

  public Attester(String attesterId) {
    this.attesterId = attesterId;
  }
}