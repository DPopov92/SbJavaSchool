package ru.sber.javaschool.atm;

import lombok.Data;
import ru.sber.javaschool.card.Card;

import java.util.Scanner;

@Data
public class MainTerminal implements Terminal{
    private static final int PIN_BLOCK_COUNT = 3;
    private CardReader cardReader;
    private Card currentCard;

    public MainTerminal() {
        this.cardReader = new CardReader();
    }

    @Override
    public void start() {
        currentCard = cardReader.getInputCard();
    }

    @Override
    public boolean checkPin(int pin) {
        return currentCard.getPin() == pin;
    }

    @Override
    public void showBalance() {
        System.out.println("Ваш баланс: " + currentCard.getBalance() + " " + currentCard.getCurrency());
    }

    @Override
    public void extractCard(Card currentCard) {
        cardReader.extract(currentCard);
    }

    @Override
    public void authentication() {
        int tryCount = 0;
        int inputPin;
        boolean isPinCorrect = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("ВВедите PIN-код: ");
        do{
            inputPin = scanner.nextInt();
            isPinCorrect = checkPin(inputPin);
            tryCount++;
            if(tryCount < PIN_BLOCK_COUNT) {
                if (!isPinCorrect) {
                    System.out.println("PIN-код неверен, введите снова: ");
                }
            }else{
                System.out.println("Попытки иссякли");
                extractCard(currentCard);
            }
        }while(!isPinCorrect);
    }

}
