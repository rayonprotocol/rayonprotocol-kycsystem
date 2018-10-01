package io.rayonprotocol.kycsystem.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.web3j.crypto.CipherException;

import io.rayonprotocol.kycsystem.datacontroller.KycAttestationDataController;
import io.rayonprotocol.kycsystem.exception.CredentialNotInitiatedException;
import io.rayonprotocol.kycsystem.model.ErrorDetails;
import io.rayonprotocol.kycsystem.model.Attestation;
import io.rayonprotocol.kycsystem.model.AttestationRequestBody;
import io.rayonprotocol.kycsystem.model.Attester;
import io.rayonprotocol.kycsystem.model.CredentialRequestBody;

@Controller
public class KycAttestationController {

  @Autowired
  KycAttestationDataController dataController;

  @RequestMapping("/")
  public String index(Model model) {
    return "index";
  }

  @RequestMapping("/health")
  @ResponseStatus(HttpStatus.OK)
  public void health(Model model) {}

  @RequestMapping("/attester")
  @ResponseBody
  public Attester attester() throws CredentialNotInitiatedException {
    return new Attester(dataController.getAttesterAddress());
  }

  @RequestMapping(value = "/attest", method = RequestMethod.POST)
  @ResponseBody
  public Attestation attest(@Valid @RequestBody AttestationRequestBody attestationReqestBody)
      throws IOException, CipherException, CredentialNotInitiatedException {
    return dataController.signWithAddressPrefix(attestationReqestBody.getUserAuth(),
        attestationReqestBody.getUserId());
  }

  @RequestMapping(value = "/initcredential", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public void initCredential(@Valid @RequestBody CredentialRequestBody credentialRequestBody, HttpServletRequest request)
      throws IOException, CipherException {
    // only local request accepted
    if (!request.getRemoteAddr().equals(request.getLocalAddr())) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }
    dataController.initWalletCredential(credentialRequestBody.getPassword());
  }

  @ExceptionHandler(CredentialNotInitiatedException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorDetails handleCredentialNotInitiatedException(CredentialNotInitiatedException ex, WebRequest request) {
    return new ErrorDetails(new Date(), 500, "Internal Server Error", ex.getMessage(), request.getDescription(false));
  }

}
