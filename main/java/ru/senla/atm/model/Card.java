package main.java.ru.senla.atm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Card {

    private long balance;
    private String number;
    private int pin;
    private boolean isBlocked;
    private LocalDateTime blockingDateTime;

    public void setBlocked(boolean blocked) {
        this.isBlocked = blocked;
        if (isBlocked) {
            this.blockingDateTime = LocalDateTime.now();
        } else {
            this.blockingDateTime = LocalDateTime.MIN;
        }
    }
}
