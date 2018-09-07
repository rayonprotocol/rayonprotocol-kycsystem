import { Express, Request, Response } from 'express';
import autobind from 'autobind-decorator'

import kycAttestationAgent from '../agent/KycAttestationAgent';
import { ATTEST_URL, AttestReqBody } from '../model/KycAttestation';

@autobind
class KycAttestationController {

  onAppStart(app: Express) {
    app.post(ATTEST_URL, this.attest);
  }

  public async attest(req: Request, res: Response) {
    const { personalId } = req.body as AttestReqBody;
    if (typeof personalId === 'undefined') {
      return res.status(400).send('"personalId" is required');
    }
    const { messageHash, v, r, s, attesterAddress } = this.sign(personalId);
    return res.send({ attesterAddress, messageHash, v, r, s, });
  }

  public sign(message: string) {
    const web3 = kycAttestationAgent.getWeb3();
    const wallet = kycAttestationAgent.getWallet();
    const attesterAddress = wallet.getAddressString();
    const { messageHash, v, r, s } = web3.eth.accounts.sign(message, wallet.getPrivateKeyString());
    return { attesterAddress, messageHash, v, r, s, }
  }

}

export default new KycAttestationController();