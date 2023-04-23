package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the game!");
            System.out.println("Enter game mode (Base, Tournament):");
            String mode = scanner.next();
            while (!(mode.equals("Base") || mode.equals("Tournament"))) {
                System.out.println("Wrong input, try again:");
                mode = scanner.next();
            }
            Mode game;
            if (mode.equals("Base")) {
                game = new Base();
            } else {
                game = new Tournament();
            }
            int winner = game.play();
            if (winner == -1) {
                System.out.println("Draw!");
            } else {
                System.out.println(winner + " is the winner!");
            }
        }
    }
}
