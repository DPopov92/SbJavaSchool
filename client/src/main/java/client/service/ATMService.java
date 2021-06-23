package client.service;

import client.dto.BalanceDTO;
import org.springframework.stereotype.Service;

@Service
public class ATMService {

    public BalanceDTO getClientBalance(Long clientId, Long accountId, int PIN) {

        return new BalanceDTO(10);
    }
}
