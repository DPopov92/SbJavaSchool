package ru.sber.javaschool.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.javaschool.server.dto.AccountDTO;
import ru.sber.javaschool.server.dto.BalanceDTO;
import ru.sber.javaschool.server.dto.CardDTO;
import ru.sber.javaschool.server.dto.ClientDTO;
import ru.sber.javaschool.server.entity.Account;
import ru.sber.javaschool.server.entity.Card;
import ru.sber.javaschool.server.entity.Client;
import ru.sber.javaschool.server.exception.CardNotFoundException;
import ru.sber.javaschool.server.exception.ClientNotFoundException;
import ru.sber.javaschool.server.repository.HostCrudRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class Processing {
    private final static LocalDate currentDate = LocalDate.now();
    private final ArrayList<Client> clients;
    private final HostCrudRepository hostCrudRepository;

    //    public Processing() {
//        clients = new ArrayList<>();
//        //наполняю тестовыми данными
//        clients.add(new Client()
//                .setClientId("1")
//                .setName("Petr")
//                .setSurname("Solov")
//                .setAccounts(new ArrayList<>()));
//
//        clients.get(0).getAccounts().add(new Account()
//                .setAccountId("1")
//                .setClientId("1")
//                .setNumber("282828845722")
//                .setBalance(new Balance()
//                        .setAmount(new BigDecimal("5432393.23"))
//                        .setCurrency(Currency.RUB))
//                .setCards(new ArrayList<>()));
//
//        clients.get(0).getAccounts().get(0).getCards().add(new Card()
//                .setAccountId("1")
//                .setClientId("1")
//                .setExpireDate(LocalDate.of(2024, 12, 4))
//                .setPin(777)
//                .setNumber("7777 6666 5555 4444"));
//
//        clients.get(0).getAccounts().get(0).getCards().add(new Card()
//                .setAccountId("1")
//                .setClientId("1")
//                .setExpireDate(LocalDate.of(2019, 11, 4))
//                .setPin(666)
//                .setNumber("6666 6666 5555 4444"));
//
//        clients.get(0).getAccounts().add(new Account()
//                .setAccountId("2")
//                .setClientId("1")
//                .setNumber("28282828459322")
//                .setBalance(new Balance()
//                        .setAmount(new BigDecimal("7777.21"))
//                        .setCurrency(Currency.USD)));
//
//        clients.get(0).getAccounts().get(1).getCards().add(new Card()
//                .setAccountId("2")
//                .setClientId("1")
//                .setExpireDate(LocalDate.of(2024, 12, 4))
//                .setPin(555)
//                .setNumber("7777 7777 7777 7777"));
//
//        clients.get(0).getAccounts().get(1).getCards().add(new Card()
//                .setAccountId("2")
//                .setClientId("1")
//                .setExpireDate(LocalDate.of(2019, 11, 4))
//                .setPin(444)
//                .setNumber("6666 6666 6666 6666"));
//
//    }
//
//    public boolean isCardExist(String cardNumber) {
//        boolean isExist = false;
//        if (searchCard(cardNumber) != null) {
//            isExist = true;
//        }
//        return isExist;
//    }
//
    private CardDTO searchCard(ClientDTO client, String cardNumber) {
        ArrayList<AccountDTO> clientAccounts = client.getAccountDTO();
        CardDTO currentCard = null;
        for (AccountDTO account : clientAccounts) {
            ArrayList<CardDTO> clientsCards = account.getCards();
            for (CardDTO card : clientsCards) {
                if (card.getNumber().equals(cardNumber)) {
                    currentCard = card;
                    break;
                }
            }
        }
        if (currentCard == null){
            throw new CardNotFoundException("Card not found!");
        }
            return currentCard;
    }

//    public boolean isCardExpired(String cardNumber) {
//        Card currentCard = searchCard(cardNumber);
//        return currentCard.getExpireDate().isBefore(currentDate);
//    }
//
//    public Balance getBalance(String cardNumber) {
//        ArrayList<Account> clientAccounts;
//        ArrayList<Card> clientsCards;
//        Account currentAccount = new Account();
//        for (Client client : clients) {
//            clientAccounts = client.getAccounts();
//            for (Account clientAccount : clientAccounts) {
//                clientsCards = clientAccount.getCards();
//                for (Card card : clientsCards) {
//                    if (card.getNumber().equals(cardNumber)) {
//                        currentAccount = clientAccount;
//                        break;
//                    }
//                }
//            }
//        }
//        return currentAccount.getBalance();
//    }

    public boolean checkPin(ClientDTO client, String cardNumber, int pin) {
        CardDTO currentCard = searchCard(client, cardNumber);
        return currentCard.getPin() == pin;
    }

    public ClientDTO getClient(Long id) {
        Client client = hostCrudRepository.findById(id)
                .orElseThrow(ClientNotFoundException::new);

        Set<Account> accountSet = client.getAccounts();
        ArrayList<AccountDTO> accountDTOSet = new ArrayList<>();

        for (Account account : accountSet) {
            Set<Card> cardList = account.getCards();
            ArrayList<CardDTO> cardDTOList = new ArrayList<>();
            for (Card card : cardList) {
                cardDTOList.add(new CardDTO(card.getOwnerName(), card.getOwnerSurname(), card.getPin(), card.getNumber()));
            }

            accountDTOSet.add(new AccountDTO(account.getId().intValue(),
                    new BalanceDTO(account.getBalance().getAmount(), account.getBalance().getCurrency()), account.getNumber(), cardDTOList));
        }

        return new ClientDTO(client.getId().intValue(),
                accountDTOSet, client.getName(), client.getSurname());
    }

    public List<ClientDTO> getAllClients() {
        Iterable<Client> clientIterable = hostCrudRepository.findAll();
        List<ClientDTO> clients = new ArrayList<>();

        clientIterable.forEach(
                client -> {
                    Set<Account> accountSet = client.getAccounts();
                    ArrayList<AccountDTO> accountDTOSet = new ArrayList<>();

                    for (Account account : accountSet) {
                        Set<Card> cardList = account.getCards();
                        ArrayList<CardDTO> cardDTOList = new ArrayList<>();
                        for (Card card : cardList) {
                            cardDTOList.add(new CardDTO(card.getOwnerName(), card.getOwnerSurname(), card.getPin(), card.getNumber()));
                        }
                        accountDTOSet.add(new AccountDTO(account.getId().intValue(),
                                new BalanceDTO(account.getBalance().getAmount(), account.getBalance().getCurrency()), account.getNumber(), cardDTOList));
                    }
                    clients.add(new ClientDTO(client.getId().intValue(), accountDTOSet, client.getName(), client.getSurname()));
                }
        );
        return clients;
    }
}
