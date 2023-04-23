package game;

import java.util.Scanner;

public class Game {
    private final boolean log;
    private final Player[] players;

    public Game(final boolean log, final int numberOfPlayers) {
        this.log = log;
        players = addPlayers(numberOfPlayers);
    }

    public Game(boolean log, Player[] players) {
        this.log = log;
        this.players = players;
    }

    public static Player[] addPlayers(int n) {
        Player[] a = new Player[n];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println("Enter type of player " + i + " (Human, Random, Sequential): ");
            String ans = scanner.next();
            while (!(ans.equals("Human") || ans.equals("Random") || ans.equals("Sequential"))) {
                System.out.println("Wrong type, try again: ");
                ans = scanner.next();
            }
            if (ans.equals("Human"))
                a[i] = new HumanPlayer();
            else if (ans.equals("Sequential"))
                a[i] = new SequentialPlayer();
            else
                a[i] = new RandomPlayer();
        }
        return a;
    }


    public int play(Board board) {
        while (true) {
            for (int i = 0; i < players.length; i++) {
                board.setTurn(i);
                final int result = move(board, players[i], i + 1);
                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board.getPosition());
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return players.length + 1 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
