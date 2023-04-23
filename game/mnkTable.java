package game;

import java.util.Arrays;
import java.util.Scanner;

public class mnkTable {
    private final int rows, cols;
    private final Cell[][] cells;

    public mnkTable() {
        final Scanner scanner = new Scanner(System.in);

        int n, m;
        System.out.println("Enter table's sizes (lines and columns): ");
        n = getValidInt(scanner);
        m = getValidInt(scanner);
        while (n <= 0 || m <= 0 || (long)n * m > 1e6) {
            System.out.println("Invalid table's sizes. Try again: ");
            n = getValidInt(scanner);
            m = getValidInt(scanner);
        }

        this.rows = n;
        this.cols = m;
        this.cells = new Cell[rows][cols];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }

    public mnkTable(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
    }

    public mnkTable(mnkTable other) {
        this.rows = other.rows;
        this.cols = other.cols;
        this.cells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.cells[i][j] = other.getCell(i, j);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean setCell(int row, int col, Cell cell) {
        if (0 <= row && row < rows && 0 <= col && col < cols) {
            cells[row][col] = cell;
            return true;
        }
        return false;
    }
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    private int getValidInt(Scanner scanner) {
        if (scanner.hasNextInt())
            return scanner.nextInt();
        return -1;
    }
}
