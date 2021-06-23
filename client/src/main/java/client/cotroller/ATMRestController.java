package client.cotroller;

import client.service.ATMService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import messages.Request;
import messages.RequestTypes;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@AllArgsConstructor
@Log
public class ATMRestController {
    private final ATMService atmService;

    @GetMapping("/ATM")
    public String getATMsStatus() {
        return "ATM is up";
    }

    @GetMapping("/ATM/clients/{clientId}/card/{cardNumber}/{PIN}")
    public String getClientBalance(
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardNumber") String cardNumber,
            @PathVariable("PIN") int PIN) {

        log.info("clientId " + clientId + " cardNumber " + cardNumber + " PIN " + PIN);


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Request> request = new HttpEntity<>(new Request(1, "{\"cardNumber\":\"" + cardNumber + "\",\"pin\":" + PIN + "}", RequestTypes.JSON));
        log.info("request.toString()" + request);

        ResponseEntity<String> responseEntityStr = restTemplate.
                postForEntity("http://127.0.0.1:8080/hosts/clients/" + clientId,
                        request, String.class);
        log.info("responseEntityStr.getBody()" + responseEntityStr.getBody());

        return responseEntityStr.getBody();
    }
}
