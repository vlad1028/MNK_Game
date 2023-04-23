package game;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeBoard implements Board {
    private final mnkTable table;
    private final mnkPosition position;
    private final int numberInlineToWin;
    private int emptyCells;
    private Cell turn;

    public TicTacToeBoard() {
        final Scanner scanner = new Scanner(System.in);

        this.table = new mnkTable();
        this.position = new mnkPosition(table);
        this.emptyCells = table.getRows() * table.getCols();

        int k;
        System.out.println("Enter number of elements in sequence to win: ");
        k = getValidInt(scanner);
        while (k <= 0) {
            System.out.println("Incorrect number. Try again: ");
            k = getValidInt(scanner);
        }
        this.numberInlineToWin = k;

        turn = Cell.X;

        System.out.println("Add traps? (yes/no): ");
        String ans = scanner.next().toLowerCase();
        while (!(ans.equals("yes") || ans.equals("no"))) {
            System.out.println("Please, type \"yes\" or \"no\": ");
            ans = scanner.next().toLowerCase();
        }

        if (ans.equals("yes")) {
            addTraps(30);
        }
    }

    private void addTraps(final int percentage) {
        final Random random = new Random();
        int numberOfTraps = random.nextInt(1, emptyCells * percentage / 100);
        for (int i = 0; i < numberOfTraps; i++) {
            int row = random.nextInt(table.getRows());
            int col = random.nextInt(table.getCols());
            while (table.getCell(row, col) != Cell.E) {
                row = random.nextInt(table.getRows());
                col = random.nextInt(table.getCols());
            }
            table.setCell(row, col, Cell.$);
            --emptyCells;
        }
    }

    public TicTacToeBoard(mnkTable template, int numberInlineToWin) {
        this.table = new mnkTable(template);
        this.position = new mnkPosition(table);
        this.emptyCells = table.getRows() * table.getCols();
        this.numberInlineToWin = numberInlineToWin;
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!position.isValid(move)) {
            return Result.LOSE;
        }

        int row = move.getRow();
        int col = move.getColumn();
        table.setCell(row, col, move.getValue());
        emptyCells--;

        int inRow = countTagsInRow(row, col);
        int inColumn = countTagsInColumn(row, col);

        if (inRow >= numberInlineToWin || inColumn >= numberInlineToWin) {
            return Result.WIN;
        }

        int inDiag1 = countTagsInDiag1(row, col);
        int inDiag2 = countTagsInDiag2(row, col);

        if (inDiag1 >= numberInlineToWin || inDiag2 >= numberInlineToWin) {
            return Result.WIN;
        }
        if (emptyCells == 0) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }

    private int countTags(int row, int col, int rowShift, int colShift) {
        if (!checkTag(row, col))
            return 0;

        int ans = 1;
        while (checkTag(row + rowShift, col + colShift)) {
            ans++;
            row += rowShift;
            col += colShift;
        }
        return ans;
    }

    private int countTagsInRow(int row, int col) {
        return countTags(row, col, 1, 0) + countTags(row, col, -1, 0) - 1;
    }

    private int countTagsInColumn(int row, int col) {
        return countTags(row, col, 0, 1) + countTags(row, col, 0, -1) - 1;
    }

    private int countTagsInDiag1(int row, int col) {
        return countTags(row, col, 1, 1) + countTags(row, col, -1, -1) - 1;
    }

    private int countTagsInDiag2(int row, int col) {
        return countTags(row, col, 1, -1) + countTags(row, col, -1, 1) - 1;
    }

    private boolean checkTag(int r, int c) {
        return (0 <= r && r < table.getRows() && 0 <= c && c < table.getCols()) && table.getCell(r, c) == turn;
    }

    @Override
    public void setTurn(int i) {
        Cell[] v = Cell.values();
        if (i >= v.length) {
            throw new IllegalArgumentException("Player's number is bigger than supported.");
        }
        this.turn = v[i];
    }

    int getValidInt(Scanner scanner) {
        if (scanner.hasNextInt())
            return scanner.nextInt();
        return -1;
    }
}
