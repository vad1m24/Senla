package main.java.ru.senla.atm.service;

import lombok.Data;
import main.java.ru.senla.atm.model.ATM;
import main.java.ru.senla.atm.exception.CardIsBlockedException;
import main.java.ru.senla.atm.exception.CardNotFoundException;
import main.java.ru.senla.atm.model.Card;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class LoginService {

    private static final String REGEX_CARD_NUMBER = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$";
    private static final int RETRIES_COUNT = 3;

    private final ATM atm;
    private final Set<Card> cards;
    private final FileService fileService;

    public boolean login() {
        System.out.println("Hello");
            try {
                String cardNumber = inputCardNumber();
                Card card = findCardByNumber(cardNumber);
                verifyCard(card);
                atm.setCard(card);
                return true;
            } catch (CardNotFoundException | CardIsBlockedException e) {
                System.out.println(e.getMessage());
                return false;
            }
    }

    public String inputCardNumber() {
        while (true) {
            System.out.print("Enter card number(XXXX-XXXX-XXXX-XXXX):");
            String numberCard = ConsoleService.SCANNER.nextLine();

            if (!numberCard.matches(REGEX_CARD_NUMBER)) {
                System.out.println("Card verification failed");
                continue;
            }
            return numberCard;
        }
    }

    public Card findCardByNumber(String cardNumber) throws CardNotFoundException {
        return cards.stream()
                .filter(card -> card.getNumber().equals(cardNumber))
                .findFirst()
                .orElseThrow(() -> new CardNotFoundException("CardNotFoundException")
                );
    }

    public boolean verifyPin(Card card) {
        int retries = RETRIES_COUNT;

        while (retries > 0) {
            System.out.print("Enter PIN (" + retries + " left): ");
            int pin = (int) ConsoleService.read();
            if (card.getPin() != pin) {
                System.out.println("Invalid PIN");
                retries--;
                continue;
            }
            return true;
        }
        return false;
    }

    public Card verifyCard(Card card) throws CardIsBlockedException {
        if (card.isBlocked()) {
            Duration duration = Duration.between(card.getBlockingDateTime(), LocalDateTime.now());
            if (duration.toDays() < 1) {
                throw new CardIsBlockedException("CardIsBlockedException");
            } else {
                card.setBlocked(false);
            }
        }

        if (!verifyPin(card)) {
            card.setBlocked(true);
            fileService.save(cards);
            ConsoleService.SCANNER.nextLine();
            throw new CardIsBlockedException("CardIsBlockedException");
        }
        return cards.stream()
                .filter(cardFromFile -> cardFromFile.equals(card))
                .findFirst()
                .get();
    }
}