import autobind from 'autobind-decorator';
import * as fs from 'fs';
import * as Web3 from 'web3';
import * as  secp256k1 from 'secp256k1';
import * as ethereumWallet from 'ethereumjs-wallet';

const KEYSTORE_PATH: string = process.env.KEYSTORE_PATH;
const PASSWORD: string = process.env.PASSWORD;

@autobind
class KycAttestationAgent {

  private web3;

  private wallet;

  constructor() {
    this.init();
  }

  private init() {
    // note that the way of using wallet is subject to change
    if (typeof KEYSTORE_PATH === 'undefined' || typeof PASSWORD === 'undefined') {
      throw new Error(`Both 'KEYSTORE_PATH' and 'PASSWORD' enviornment variables must be defined`);
    }
    const keystore = fs.readFileSync(KEYSTORE_PATH).toString();
    this.wallet = ethereumWallet.fromV3(keystore, PASSWORD);
    this.web3 = new Web3();
  }

  signWithAddressPrefix(message: string, prefixAddress: string) {
    const { utf8ToHex, sha3 } = this.web3.utils;
    const attesterAddress = this.wallet.getAddressString();
    const privateKey = this.wallet.getPrivateKeyString();

    const messageHash = sha3(utf8ToHex(message));
    const addressPrefixedMessage = prefixAddress.concat(messageHash.replace(/^0x/, ''));
    const addressPrefixedMessageHash = sha3(addressPrefixedMessage);

    const signArgs = [addressPrefixedMessageHash, privateKey].map(arg => Buffer.from(arg.replace(/^0x/, ''), 'hex'));

    const { signature: signatureBuffer, recovery } = secp256k1.sign(...signArgs);
    const signature = signatureBuffer.toString('hex');
    const r = '0x'.concat(signature.slice(0, 64));
    const s = '0x'.concat(signature.slice(64, 128));
    const v = Number(recovery) + 27;

    return { messageHash, attesterAddress, v, r, s, };
  }
}

export default new KycAttestationAgent();