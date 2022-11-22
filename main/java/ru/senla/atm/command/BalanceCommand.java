package main.java.ru.senla.atm.command;

import lombok.AllArgsConstructor;
import main.java.ru.senla.atm.model.ATM;

@AllArgsConstructor
public class BalanceCommand implements Command {

    private ATM atm;

    @Override
    public void execute() {
        System.out.println("BALANCE");
        atm.printBalance();
    }
}
