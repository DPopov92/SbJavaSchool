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
    private final HostCrudRepository hostCrudRepository;

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
