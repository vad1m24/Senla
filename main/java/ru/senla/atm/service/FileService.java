package main.java.ru.senla.atm.service;

import lombok.AllArgsConstructor;
import main.java.ru.senla.atm.model.Card;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FileService {

    public static final String Delimiter = " ";
    private String file;

    public void save(Set<Card> cards) {
        Set<String> cardsFromText = cards.stream()
                .map(card -> card.getBalance() + Delimiter +
                        card.getNumber() + Delimiter +
                        card.getPin() + Delimiter +
                        card.isBlocked() + Delimiter +
                        card.getBlockingDateTime())
                .collect(Collectors.toSet());

        try {
            Files.write(Paths.get(file), cardsFromText);
        } catch (IOException e) {
            System.out.println("Error writing to file" + file);
        }
    }

    public Set<Card> read() {
        List<String> text = new ArrayList<>();

        try {
            text = Files.readAllLines(Paths.get(file));
        } catch (IOException e) {
            System.out.println("Error with file");
        }

        return text.stream()
                .map(string -> string.split(Delimiter))
                .map(cardsFromText -> new Card(Long.parseLong(cardsFromText[0]),
                        cardsFromText[1],
                        Integer.parseInt(cardsFromText[2]),
                        Boolean.parseBoolean(cardsFromText[3]),
                        LocalDateTime.parse(cardsFromText[4])
                ))
                .collect(Collectors.toSet());
    }
}