package ru.sber.javaschool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class Account {
    private String accountId;
    private String clientId;
    private Balance balance;
    private String number;
    private ArrayList<Card> cards;

    public Account() {
        this.cards = new ArrayList<>();
    }
}
