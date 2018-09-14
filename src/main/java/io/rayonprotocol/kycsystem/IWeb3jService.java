package io.rayonprotocol.kycsystem;

import java.io.IOException;
import org.web3j.crypto.CipherException;

// import org.web3j.crypto.Sign.SignatureData;

public interface IWeb3jService {
    public String sign(String message) throws IOException, CipherException;
}
