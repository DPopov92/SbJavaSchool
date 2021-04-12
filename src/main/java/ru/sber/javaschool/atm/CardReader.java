package ru.sber.javaschool.atm;

import ru.sber.javaschool.card.Card;

import java.math.BigDecimal;

public class CardReader implements Reader{

    @Override
    public Card getInputCard() {
        return new Card().setOwnerName("MrOwner").setOwnerSurname("OwnersSurname").setPin(777).setBalance(new BigDecimal("582721.23")).setCurrency("RUB");
    }

    @Override
    public void extract(Card currentCard) {
        currentCard = null;
    }
}
