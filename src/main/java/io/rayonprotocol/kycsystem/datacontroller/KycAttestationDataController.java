package io.rayonprotocol.kycsystem.datacontroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import io.rayonprotocol.kycsystem.agent.WalletAgent;
import io.rayonprotocol.kycsystem.model.Attestation;

@Service
public class KycAttestationDataController {

    @Autowired
    private WalletAgent walletAgent;


    public void initWalletCredential(String password) throws IOException, CipherException {
        walletAgent.initCredentials(password);
    }

    /**
     * sign message with prefixAddress by attestor wallet
     * 
     * @param message signing message
     * @param prefixAddresss address to prefix message 
     * @return messageHash, attestorAddress, r, s, v for signuate
     * @throws IOException
     * @throws CipherException
     */
    public Attestation signWithAddressPrefix(String message, String prefixAddresss) 
            throws IOException, CipherException {
        final Attestation attestation = new Attestation();
        final String messageHash = Hash.sha3(Numeric.toHexStringNoPrefix(message.getBytes()));
        // trim hex prefix off messageHash then prefix adderess to it
        final String addressPrefixedMessage = prefixAddresss.concat(messageHash.replace("0x", ""));
        final String addressPrefixedMessageHash = Hash.sha3(addressPrefixedMessage);

        Sign.SignatureData signature = Sign.signMessage(
            Numeric.hexStringToByteArray(addressPrefixedMessageHash),
            walletAgent.getEcKeyPair(),
            false // no need to hash message again
        );
        attestation.setMessageHash(messageHash);
        attestation.setAttesterAddress(walletAgent.getAddress());
        attestation.setR(Numeric.toHexString(signature.getR()));
        attestation.setS(Numeric.toHexString(signature.getS()));
        attestation.setV(Integer.toString(signature.getV()));
        return attestation;
    }

    public String getAttesterAddress() {
        return walletAgent.getAddress();
    }
}
