package ru.sber.javaschool.atm;

import ru.sber.javaschool.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Processing {
    private final static LocalDate currentDate = LocalDate.now();
    private ArrayList<Client> clients;

    public Processing() {
        clients = new ArrayList<>();
        //наполняю тестовыми данными
        clients.add(new Client()
                .setClientId("1")
                .setName("Petr")
                .setSurname("Solov")
                .setAccounts(new ArrayList<>()));

        clients.get(0).getAccounts().add(new Account()
                .setAccountId("1")
                .setClientId("1")
                .setNumber("282828845722")
                .setBalance(new Balance()
                        .setAmount(new BigDecimal("5432393.23"))
                        .setCurrency(Currency.RUB))
                .setCards(new ArrayList<>()));

        clients.get(0).getAccounts().get(0).getCards().add(new Card()
                .setAccountId("1")
                .setClientId("1")
                .setExpireDate(LocalDate.of(2024, 12, 4))
                .setPin(777)
                .setNumber("7777 6666 5555 4444"));

        clients.get(0).getAccounts().get(0).getCards().add(new Card()
                .setAccountId("1")
                .setClientId("1")
                .setExpireDate(LocalDate.of(2019, 11, 4))
                .setPin(666)
                .setNumber("6666 6666 5555 4444"));

        clients.get(0).getAccounts().add(new Account()
                .setAccountId("2")
                .setClientId("1")
                .setNumber("28282828459322")
                .setBalance(new Balance()
                        .setAmount(new BigDecimal("7777.21"))
                        .setCurrency(Currency.USD)));

        clients.get(0).getAccounts().get(1).getCards().add(new Card()
                .setAccountId("2")
                .setClientId("1")
                .setExpireDate(LocalDate.of(2024, 12, 4))
                .setPin(555)
                .setNumber("7777 7777 7777 7777"));

        clients.get(0).getAccounts().get(1).getCards().add(new Card()
                .setAccountId("2")
                .setClientId("1")
                .setExpireDate(LocalDate.of(2019, 11, 4))
                .setPin(444)
                .setNumber("6666 6666 6666 6666"));

    }

    public boolean isCardExist(String cardNumber) {
        boolean isExist = false;
        if (searchCard(cardNumber) != null) {
            isExist = true;
        }
        return isExist;
    }

    private Card searchCard(String cardNumber) {
        ArrayList<Account> clientAccounts;
        ArrayList<Card> clientsCards;
        Card currentCard = null;
        for (Client client : clients) {
            clientAccounts = client.getAccounts();
            for (Account clientAccount : clientAccounts) {
                clientsCards = clientAccount.getCards();
                for (Card card : clientsCards) {
                    if (card.getNumber().equals(cardNumber)) {
                        currentCard = card;
                        break;
                    }
                }
            }
        }
        return currentCard;
    }

    public boolean isCardExpired(String cardNumber) {
        Card currentCard = searchCard(cardNumber);
        return currentCard.getExpireDate().isBefore(currentDate);
    }

    public Balance getBalance(String cardNumber) {
        ArrayList<Account> clientAccounts;
        ArrayList<Card> clientsCards;
        Account currentAccount = new Account();
        for (Client client : clients) {
            clientAccounts = client.getAccounts();
            for (Account clientAccount : clientAccounts) {
                clientsCards = clientAccount.getCards();
                for (Card card : clientsCards) {
                    if (card.getNumber().equals(cardNumber)) {
                        currentAccount = clientAccount;
                        break;
                    }
                }
            }
        }
        return currentAccount.getBalance();
    }

    public boolean checkPin(String cardNumber, int pin) {
        Card currentCard = searchCard(cardNumber);
        return currentCard.getPin() == pin;
    }
}
