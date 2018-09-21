package io.rayonprotocol.kycsystem;

import java.io.IOException;
import java.util.Map;

import org.web3j.crypto.CipherException;

// import org.web3j.crypto.Sign.SignatureData;

public interface IWeb3jService {
    public Map<String, String> signWithAddressPrefix(String message, String prefixAddresss) throws IOException, CipherException;
}
