package io.rayonprotocol.kycsystem;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.crypto.WalletUtils;
// import org.web3j.crypto.Sign.SignatureData;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.utils.Numeric;

/**
 * Sample service to demonstrate web3j bean being correctly injected.
 */
@Service
public class Web3jServiceImpl implements IWeb3jService {

    @Autowired
    private Web3j web3j;
    
    @Autowired
    private WalletCredentialConfig walletCredentialConfig;

    private Credentials credentials;
    
    public String sign(String message) throws IOException, CipherException {
        if (credentials == null) {
            // credentials = WalletUtils.loadCredentials(walletCredentialConfig.getPassword(), walletCredentialConfig.getSource());
            credentials = WalletUtils.loadCredentials(walletCredentialConfig.getPassword(), walletCredentialConfig.getSource());
        }        
        // String hash = Hash.sha3(Numeric.toHexStringNoPrefix(message.getBytes()));
        // String hashedMessage = "\\x19Ethereum Signed Message:\n" + hash.length() + hash;
        // byte[] data = hashedMessage.getBytes();
        // Sign.SignatureData signature = Sign.signMessage(data, credentials.getEcKeyPair());
        // System.out.println("R: " + Numeric.toHexString(signature.getR()));
        // System.out.println("S: " + Numeric.toHexString(signature.getS()));
        // System.out.println("V: " + Integer.toString(signature.getV()));
        
        // TODO: to return signature not address
        return credentials.getAddress();
    }
}
