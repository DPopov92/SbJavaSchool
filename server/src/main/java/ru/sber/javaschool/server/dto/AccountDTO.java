package ru.sber.javaschool.server.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.sber.javaschool.server.entity.Balance;

import java.util.ArrayList;

@RequiredArgsConstructor
@Getter
public class AccountDTO {
    private final int accountId;
    private final BalanceDTO balance;
    private final String number;
    private final ArrayList<CardDTO> cards;
}
