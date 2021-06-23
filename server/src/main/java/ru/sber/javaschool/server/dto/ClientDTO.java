package ru.sber.javaschool.server.dto;

import lombok.Value;
import ru.sber.javaschool.server.entity.Account;

import java.util.ArrayList;
import java.util.List;

@Value
public class ClientDTO {
    private final int clientId;
 //   private final int PIN;
    private final ArrayList<AccountDTO> accountDTO;
    private final String name;
    private final String surname;

}
