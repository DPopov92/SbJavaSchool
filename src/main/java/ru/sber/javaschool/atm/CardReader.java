package ru.sber.javaschool.atm;

import ru.sber.javaschool.model.Card;

public class CardReader implements Reader {

    @Override
    public Card getInputCard() {
        return new Card().setNumber("7777 7777 7777 7777");
    }

    @Override
    public void extract(Card currentCard) {
        currentCard = null;
    }
}
