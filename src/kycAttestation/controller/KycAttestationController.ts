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
    const { personalId, address } = req.body as AttestReqBody;
    if (typeof personalId === 'undefined' || typeof address === 'undefined') {
      return res.status(400).send('both "personalId" and "address" are required');
    }

    const signautre = kycAttestationAgent.signWithAddressPrefix(personalId, address);
    return res.send(signautre);
  }

}

export default new KycAttestationController();