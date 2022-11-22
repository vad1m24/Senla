package main.java.ru.senla.atm.exception;

public class DepositLimitException extends Exception{

    public DepositLimitException(String message) {
        super(message);
    }
}
