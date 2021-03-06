package io.rayonprotocol.kycsystem.agent;

import java.io.IOException;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;

import io.rayonprotocol.kycsystem.exception.CredentialNotInitiatedException;
import io.rayonprotocol.kycsystem.model.WalletCredentialConfig;

@Component
public class WalletAgent {
    @Autowired
    private WalletCredentialConfig walletCredentialConfig;

    private Credentials credentials;

    /**
     * load crediential with password and pre-configured keystore
     * @param password for keystore
     * @throws IOException
     * @throws CipherException
     */
    public void initCredentials(@NotBlank String password) throws IOException, CipherException {
        this.credentials = WalletUtils.loadCredentials(password, walletCredentialConfig.getSource());
    }

    final public String getAddress() throws CredentialNotInitiatedException {
        if (this.credentials == null) throw new CredentialNotInitiatedException();
        return this.credentials.getAddress();
    }

    final public ECKeyPair getEcKeyPair() throws CredentialNotInitiatedException {
        if (this.credentials == null) throw new CredentialNotInitiatedException();
        return this.credentials.getEcKeyPair();
    }
}
