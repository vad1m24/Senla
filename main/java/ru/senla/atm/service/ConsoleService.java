package main.java.ru.senla.atm.service;

import java.util.Scanner;

public class ConsoleService {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static long read() {
        long number;

        do {
            while (!SCANNER.hasNextLong()) {
                System.out.print("Enter an integer");
                SCANNER.next();
            }
            number = SCANNER.nextLong();
            if (number < 0) {
                System.out.print("Enter a positive integer");
            }
        } while (number < 0);
        return number;
    }
}