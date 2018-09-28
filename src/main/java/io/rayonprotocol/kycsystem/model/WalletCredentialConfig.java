package io.rayonprotocol.kycsystem.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix="wallet.credential")
@Data
public class WalletCredentialConfig {
  
  /**
   * keystore file path
   */
  private String source;
  
  private String password;
}