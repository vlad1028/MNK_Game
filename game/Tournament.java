package game;

import java.util.Arrays;
import java.util.Scanner;

public class Tournament implements Mode {
    private int[] points;
    final private Player[] players;
    final private Scanner scanner = new Scanner(System.in);
    final private mnkTable template;
    final private int numberToWin;

    public Tournament() {
        System.out.println("Enter number of players:");
        int n = getValidInt();
        while (n < 0) {
            System.out.println("Wrong input, try again");
            n = getValidInt();
        }
        points = new int[n];
        for (int i = 0; i < n; i++)
            points[i] = 0;

        template = new mnkTable();
        int k;
        System.out.println("Enter number of elements in sequence to win: ");
        k = getValidInt();
        while (k <= 0) {
            System.out.println("Incorrect number. Try again: ");
            k = getValidInt();
        }
        numberToWin = k;
        players = Game.addPlayers(n);
    }

    @Override
    public int play() {
        for (int i = 1; i <= players.length; i++) {
            for (int j = i + 1; j <= players.length; j++) {
                System.out.println(i + " (X) and " + j + " (O) play now!");
                int result1 = round(i, j);
                printResult(result1);

                System.out.println("Second round: " + i + " (O), and " + j + " (X)");
                int result2 = round(j, i);
                printResult(result2);
            }
        }
        System.out.println("Tournament is finished!");
        System.out.println("Points:");
        for (int i = 1; i <= players.length; ++i) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(Arrays.toString(points));
        return getBestPlayer();
    }



    private int round(int firstPlayer, int secondPlayer) {
        final Game game = new Game(false, new Player[]{players[firstPlayer - 1], players[secondPlayer - 1]});
        final int result =  game.play(new TicTacToeBoard(template, numberToWin));
        addPoints(result, firstPlayer, secondPlayer);
        return result;
    }

    private void addPoints(int result, int i, int j) {
        if (result == 0) {
            points[i - 1]++;
            points[j - 1]++;
        } else if (result == 1) {
            points[i - 1] += 3;
        } else if (result == 2) {
            points[j - 1] += 3;
        }
    }

    private void printResult(int result) {
        System.out.println("Game result:");
        if (result == 0) {
            System.out.println("DRAW");
        } else if (result == 1) {
            System.out.println("X wins!");
        } else if (result == 2) {
            System.out.println("O wins!");
        }
    }

    int getBestPlayer() {
        int ans = 0;
        int cntBests = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i] > ans) {
                ans = i;
            } else if (points[i] == ans) {
                cntBests++;
            }
        }
        if (cntBests != 1)
            return -1;
        return ans;
    }

    int getValidInt() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return -1;
    }
}
