package main.java.ru.senla.atm.model;

import lombok.Data;
import main.java.ru.senla.atm.exception.DepositLimitException;
import main.java.ru.senla.atm.exception.NotEnoughMoneyException;

@Data
public class ATM {

    private static final int MAX_DEPOSIT_AMOUNT = 1_000_000;
    private long atmBalance;
    private Card card;

    public void printBalance() {
        System.out.println("Balance: " + card.getBalance());
    }

    public void deposit(Long amount) throws DepositLimitException {
        if (amount > MAX_DEPOSIT_AMOUNT) {
            throw new DepositLimitException("DepositLimitException");
        }
        card.setBalance(card.getBalance() + amount);
    }

    public void withdraw(Long amount) throws NotEnoughMoneyException {
        if (amount > card.getBalance()) {
            throw new NotEnoughMoneyException("NotEnoughMoneyException");
        }
        else if (amount > atmBalance) {
            throw new NotEnoughMoneyException("NotEnoughMoneyException");
        }
        card.setBalance(card.getBalance() - amount);
    }
}