package io.rayonprotocol.kycsystem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
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

    public Map<String, String> signWithAddressPrefix(String message, String prefixAddresss)
            throws IOException, CipherException {
        Map<String, String> map = new HashMap<String, String>();

        if (credentials == null) {
            // credentials =
            // WalletUtils.loadCredentials(walletCredentialConfig.getPassword(),
            // walletCredentialConfig.getSource());
            credentials = WalletUtils.loadCredentials(walletCredentialConfig.getPassword(),
                    walletCredentialConfig.getSource());
        }
        String attesterAddress = credentials.getAddress();

        String messageHash = Hash.sha3(Numeric.toHexStringNoPrefix(message.getBytes()));
        String addressPrefixedMessage = prefixAddresss.concat(messageHash.replace("0x", ""));
        String addressPrefixedMessageHash = Hash.sha3(addressPrefixedMessage);
        byte[] addressPrefixedMessageHashBytes = Hash.sha3(addressPrefixedMessage.getBytes());
        
        Sign.SignatureData signature = Sign.signMessage(Numeric.hexStringToByteArray(addressPrefixedMessageHash),
                credentials.getEcKeyPair(), false);

        map.put("messageHash", messageHash);
        map.put("attesterAddress", attesterAddress);
        map.put("r", Numeric.toHexString(signature.getR()));
        map.put("s", Numeric.toHexString(signature.getS()));
        map.put("v", Integer.toString(signature.getV()));
        
        return map;
    }
}
