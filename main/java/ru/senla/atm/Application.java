package main.java.ru.senla.atm;

import main.java.ru.senla.atm.command.*;
import main.java.ru.senla.atm.model.ATM;
import main.java.ru.senla.atm.model.Card;
import main.java.ru.senla.atm.service.ConsoleService;
import main.java.ru.senla.atm.service.FileService;
import main.java.ru.senla.atm.service.LoginService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Application {

    public static void main(String[] args) {

        ATM atm = initATM();
        FileService fileService = new FileService("db.txt");
        Set<Card> cards = fileService.read();
        Map<Long, Command> commands = initConsoleCommands(atm, fileService, cards);
        boolean islogin = new LoginService(atm, cards, fileService).login();

        long option = 0;
        if (!islogin) {
            option = 4;
        }
        while (option != 4) {
            System.out.print("ATM\n" +
                    "1 - Balance\n" +
                    "2 - Withdraw\n" +
                    "3 - Deposit\n" +
                    "4 - Exit\n" +
                    "Choose option:\n");
            try {
                option = ConsoleService.read();
                commands.get(option).execute();
            } catch (NullPointerException e) {
                System.out.println("SMTH went wrong");
            }
        }
    }

    private static Map<Long, Command> initConsoleCommands(ATM atm, FileService fileService, Set<Card> cards) {
        Map<Long, Command> commands = new HashMap<>();
        commands.put(1L, new BalanceCommand(atm));
        commands.put(2L, new WithdrawCommand(atm));
        commands.put(3L, new DepositCommand(atm));
        commands.put(4L, new ExitCommand(fileService, cards));
        return commands;
    }

    private static ATM initATM() {
        ATM atm = new ATM();
        atm.setAtmBalance(999999999);
        return atm;
    }
}