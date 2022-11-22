package main.java.ru.senla.atm.command;

import lombok.AllArgsConstructor;
import main.java.ru.senla.atm.model.Card;
import main.java.ru.senla.atm.service.FileService;

import java.util.Set;

@AllArgsConstructor
public class ExitCommand implements Command {

    private FileService fileService;
    private Set<Card> cards;

    @Override
    public void execute() {
        System.out.print("Exit");
        fileService.save(cards);
    }
}