package main.java.ru.senla.atm.exception;

public class NotEnoughMoneyException extends Exception{

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}