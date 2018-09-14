package io.rayonprotocol.kycsystem;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix="wallet.credential")
public class WalletCredentialConfig {
  
  /**
   * keystore file path
   */
  @Getter @Setter
  private String source;
  
  @Getter @Setter
  private String password;
}