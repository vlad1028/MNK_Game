package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int row = getValidInt();
            int col = getValidInt();
            while (row < 0 || col < 0) {
                out.println("Wrong input, enter row and column again");
                row = getValidInt();
                col = getValidInt();
            }
            final Move move = new Move(row, col, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }

    private int getValidInt() {
        if (in.hasNextInt()) {
            return in.nextInt();
        }
        return -1;
    }
}
