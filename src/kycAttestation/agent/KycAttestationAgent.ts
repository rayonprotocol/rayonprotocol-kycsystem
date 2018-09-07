import autobind from 'autobind-decorator';
import * as fs from 'fs';
import * as Web3 from 'web3';
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

  getWeb3() {
    return this.web3
  }

  getWallet() {
    return this.wallet
  }
}

export default new KycAttestationAgent();