package io.rayonprotocol.kycsystem;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.CipherException;

@RequestMapping("/attestation")
@Controller
public class KycAttestationController {

  @Autowired
  IWeb3jService web3jService;

  @RequestMapping("/")
  public String main(Model model) {
    model.addAttribute("name", "hello");
    return "hello";
  }

  @RequestMapping(value = "/{address}", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, String> attest(@PathVariable("address") String address, @RequestParam("personalId") String personalId) throws IOException, CipherException {
    return web3jService.signWithAddressPrefix(personalId, address);
  }
}
