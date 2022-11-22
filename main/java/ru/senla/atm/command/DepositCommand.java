package main.java.ru.senla.atm.command;

import lombok.AllArgsConstructor;
import main.java.ru.senla.atm.model.ATM;
import main.java.ru.senla.atm.exception.DepositLimitException;
import main.java.ru.senla.atm.service.ConsoleService;

@AllArgsConstructor
public class DepositCommand implements Command {

    private ATM atm;

    @Override
    public void execute() {
        System.out.print("Deposit:");

        Long amount = ConsoleService.read();
        try {
            atm.deposit(amount);
        } catch (DepositLimitException e) {
            System.out.println(e.getMessage());
        }
    }
}