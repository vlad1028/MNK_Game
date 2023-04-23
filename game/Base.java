package game;

import java.io.PrintStream;
import java.util.Scanner;

public class Base implements Mode {
    private final PrintStream out;
    private final Scanner in;

    public Base() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }
    public int play() {
        out.println("Enter number of players:");
        int n = getValidInt();
        while (n < 0) {
            System.out.println("Wrong input, try again");
            n = getValidInt();
        }
        final Game game = new Game(true, n);
        int result = game.play(new TicTacToeBoard());
        return result;
    }

    private int getValidInt() {
        if (in.hasNextInt()) {
            return in.nextInt();
        }
        return -1;
    }
}
