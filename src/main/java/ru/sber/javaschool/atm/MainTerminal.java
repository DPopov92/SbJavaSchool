package ru.sber.javaschool.atm;

import lombok.Data;
import ru.sber.javaschool.model.Balance;
import ru.sber.javaschool.model.Card;

import java.util.Scanner;

@Data
public class MainTerminal implements Terminal {
    private static final int PIN_BLOCK_COUNT = 3;
    private CardReader cardReader;
    private Processing processing;
    private Card currentCard;

    public MainTerminal() {
        this.cardReader = new CardReader();
        this.processing = new Processing();
    }

    @Override
    public void start() {
        currentCard = cardReader.getInputCard();
    }


    @Override
    public void showBalance() {
        Balance currentBalance = processing.getBalance(currentCard.getNumber());
        System.out.println("Ваш баланс: " + currentBalance.getAmount() + " " + currentBalance.getCurrency());
    }

    @Override
    public void extractCard(Card currentCard) {
        cardReader.extract(currentCard);
    }

    @Override
    public boolean authentication() {
        if (validateCard()) {
            if (isCardExpired()) {
                return checkPin();
            } else return false;
        } else return false;

    }

    private boolean validateCard() {
        if (!processing.isCardExist(currentCard.getNumber())) {
            System.out.println("Карта невалидна. Обратитесь в отделение за помощью");
            extractCard(currentCard);
            return false;
        } else
            return true;
    }

    private boolean isCardExpired() {
        if (processing.isCardExpired(currentCard.getNumber())) {
            System.out.println("Срок действия карты истёк. Обратитесь в отделение за помощью");
            extractCard(currentCard);
            return false;
        } else return true;
    }

    private boolean checkPin() {
        int tryCount = 0;
        int inputPin;
        boolean isPinCorrect = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("ВВедите PIN-код: ");
        do {
            inputPin = scanner.nextInt();
            isPinCorrect = processing.checkPin(currentCard.getNumber(), inputPin);
            tryCount++;
            if (tryCount < PIN_BLOCK_COUNT) {
                if (!isPinCorrect) {
                    System.out.println("PIN-код неверен, введите снова: ");
                }
            } else {
                System.out.println("Попытки иссякли");
                extractCard(currentCard);
            }
        } while (!isPinCorrect && tryCount != PIN_BLOCK_COUNT);
        return isPinCorrect;
    }
}
