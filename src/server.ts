require('dotenv').config();

import * as express from 'express';
import * as bodyParser from 'body-parser'

import kycAttestationController from './kycAttestation/controller/KycAttestationController';

const PORT = Number(process.env.PORT) || 3000;
const app: express.Express = express().use(bodyParser.json());

kycAttestationController.onAppStart(app);

// app startup
app.listen(PORT, () => {
  console.log(`Server is running at http://localhost:${PORT}`);
});