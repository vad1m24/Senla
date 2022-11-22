package main.java.ru.senla.atm.command;

import lombok.AllArgsConstructor;
import main.java.ru.senla.atm.model.ATM;
import main.java.ru.senla.atm.exception.NotEnoughMoneyException;
import main.java.ru.senla.atm.service.ConsoleService;

@AllArgsConstructor
public class WithdrawCommand implements Command {

    private ATM atm;

    @Override
    public void execute() {
        System.out.print("Withdraw:");

        Long amount = ConsoleService.read();
        try {
            atm.withdraw(amount);
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
    }

}
