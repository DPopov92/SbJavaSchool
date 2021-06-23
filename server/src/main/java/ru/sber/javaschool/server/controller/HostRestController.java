package ru.sber.javaschool.server.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import messages.Request;
import messages.Response;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import ru.sber.javaschool.server.dto.BalanceDTO;
import ru.sber.javaschool.server.dto.ClientDTO;
import ru.sber.javaschool.server.exception.PinNotCorrectException;
import ru.sber.javaschool.server.service.Processing;


import java.util.List;

@RestController
@AllArgsConstructor
@Log
public class HostRestController {

    private Processing clientService;

    @GetMapping("/hosts")
    public String HealthCheck() {
        return "{data: \"Host is up\"}";
    }

    @GetMapping("/hosts/clients")
    public List<ClientDTO> getClientsInfo() {
        return clientService.getAllClients();

    }

    @PostMapping("/hosts/clients/{clientId}")
    public Response getBalance(@PathVariable("clientId") Long clientId,
                               @RequestBody Request request) {


        log.info(request.toString());
        ClientDTO currentClient = clientService.getClient(clientId);

        JSONObject jsonObject = new JSONObject(request.getData());

        if(!clientService.checkPin(currentClient,jsonObject.getString("cardNumber"),jsonObject.getInt("pin"))){
            throw new PinNotCorrectException("Pin is not correct!");
        }

        BalanceDTO balanceDTO = clientService.getClient(clientId).getAccountDTO().get(0).getBalance();
        Response response = new Response(balanceDTO.getAmount(), balanceDTO.getCurrency());
        log.info(response.toString());
        return response;
    }
}
